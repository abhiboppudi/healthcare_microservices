-- ============================================================
--  APPOINTMENT SERVICE DATABASE SCHEMA (PostgreSQL)
-- ============================================================

-- ===========================
-- 1. appointment (core table)
-- ===========================
create table if not exists appointment (
    appointment_id      bigserial primary key,
    patient_id          bigint not null,
    doctor_id           bigint not null,
    hospital_id         bigint not null,

    appointment_time    timestamp not null,

    status              varchar(50) not null,   -- Enum: PENDING, CONFIRMED, etc.
    type                varchar(50) not null,   -- Enum: IN_PERSON, VIDEO, PHONE

    created_at          timestamp default now(),
    updated_at          timestamp default now()
);

-- Indexes for fast lookups
create index if not exists idx_appointment_doctor   on appointment(doctor_id);
create index if not exists idx_appointment_patient  on appointment(patient_id);
create index if not exists idx_appointment_hospital on appointment(hospital_id);
create index if not exists idx_appointment_time     on appointment(appointment_time);


-- ==================================
-- 4. appointment_cancellation (1-to-1)
-- ==================================
create table if not exists appointment_cancellation (
    appointment_id      bigint primary key
                        references appointment(appointment_id) on delete cascade,

    cancelled_at        timestamp,
    cancelled_by        bigint,       -- patientId or doctorId
    cancelled_by_type   varchar(50),  -- e.g. PATIENT, DOCTOR, ADMIN
    cancellation_reason text
);

-- ==================================
-- 5. appointment_followup (1-to-many)
-- ==================================
create table if not exists appointment_followup (
    followup_id         bigserial primary key,
    appointment_id      bigint not null
                        references appointment(appointment_id) on delete cascade,
    followup_required   boolean default false,
    followup_date       timestamp,
    followup_reason     text,
    followup_notes      text,
    recommended_by      bigint,       -- doctorId or system user
    status              varchar(50),  -- ACTIVE, COMPLETED, CANCELLED
    cancelled_at        timestamp,
    cancellation_reason text,
    cancelled_by_id     bigint,
    created_at          timestamp default now(),
    updated_at          timestamp default now()
);

-- ==================================
-- 6. appointment_details (1-to-1 per followup)
-- ==================================
create table if not exists appointment_details (
    details_id          bigserial primary key,
    followup_id         bigint not null
                        references appointment_followup(followup_id) on delete cascade,
    reason              text,
    patient_notes       text,
    doctor_notes        text,
    source              varchar(50) -- MOBILE_APP, WEB, ADMIN_PORTAL
);

-- ==================================
-- 7. appointment_payment (1-to-1 per followup)
-- ==================================
create table if not exists appointment_payment (
    payment_record_id   bigserial primary key,
    followup_id         bigint not null
                        references appointment_followup(followup_id) on delete cascade,
    payment_required    boolean,
    payment_completed   boolean,
    payment_id          bigint, -- Reference to Billing Service
    consultation_fee    DOUBLE PRECISION
);

-- ==================================
-- 8. appointment_metadata (1-to-many, KV)
-- ==================================
create table if not exists appointment_metadata (
    id                  bigserial primary key,
    appointment_id      bigint not null
                        references appointment(appointment_id) on delete cascade,
    meta_key            varchar(100) not null,
    meta_value          text,
    created_at          timestamp default now()
);

-- ==================================
-- Indexes
-- ==================================
create index if not exists idx_followup_appointment on appointment_followup(appointment_id);
create index if not exists idx_details_followup     on appointment_details(followup_id);
create index if not exists idx_payment_followup     on appointment_payment(followup_id);
create index if not exists idx_metadata_appointment on appointment_metadata(appointment_id);

-- ============================================================
-- END OF FILE
-- ============================================================
