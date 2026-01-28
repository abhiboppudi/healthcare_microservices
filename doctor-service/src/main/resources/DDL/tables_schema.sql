-- Doctor
create table if not exists doctor (
    doctor_id bigserial primary key,
    first_name varchar(255),
    last_name varchar(255),
    gender varchar(50),
    email_id varchar(255),
    phone_number varchar(50),
    license_number varchar(100),
    active_status boolean not null default true,
    specialization varchar(255),
    years_of_experience int,
    qualifications varchar(255),
    consultation_fees DOUBLE PRECISION,
    created_date timestamp,
    created_by varchar(255),
    last_updated_date timestamp,
    last_updated_by varchar(255)
);

-- Doctor Metrics
create table if not exists doctor_metrics (
    metrics_id bigserial primary key,
    doctor_id bigint not null,
    average_stars numeric(3,2), -- average rating (e.g., 4.75)
    total_recommendations int default 0,
    total_positive_feedback int default 0,
    total_negative_feedback int default 0,
    constraint fk_doctor foreign key (doctor_id) references doctor (doctor_id)
);

-- Hospital Assignment
create table if not exists hospital_assignment (
    id bigserial primary key,
    doctor_id bigint not null,
    hospital_id bigint not null,
    assigned_at timestamp not null default current_timestamp,
    active boolean not null default true,
    constraint fk_doctor_assignment foreign key (doctor_id) references doctor (doctor_id)
    -- hospital_id should reference hospital(id) once hospital table exists
);

-- Patient Feedback
create table if not exists doctor_feedback (
    feedback_id bigserial primary key,
    doctor_id bigint not null unique,
    total_stars decimal,
    feedback_count bigint not null,
    positive boolean not null,
    created_at timestamp not null default current_timestamp,
    constraint fk_doctor_feedback foreign key (doctor_id) references doctor (doctor_id)
    -- patient_id should reference patient(id) once patient table exists
);

-- Prescription
create table if not exists prescription (
    id bigserial primary key,
    doctor_id bigint not null,
    patient_id bigint not null,
    appointment_id bigint,
    hospital_id bigint,
    issued_date date not null,
    valid_until date,
    status varchar(50) not null,
    notes text,
    constraint fk_prescription_doctor foreign key (doctor_id) references doctor (doctor_id)
    -- patient_id → patient(id), hospital_id → hospital(id) once those tables exist
);

-- Comment
create table if not exists comment (
    comment_id bigserial primary key,
    doctor_id bigserial not null,
    author_id bigint not null, -- doctor/patient id
    content text not null,
    star_count int check (star_count between 1 and 5),
    created_at timestamp not null default current_timestamp,
    active boolean not null default true,
    constraint fk_comment_doctor foreign key (doctor_id) references doctor (doctor_id)
);

-- Comment Reply
create table if not exists comment_reply (
    id bigserial primary key,
    comment_id bigint not null,
    replier_id bigint not null,
    content text not null,
    created_at timestamp not null default current_timestamp,
    active boolean not null default true,
    constraint fk_comment foreign key (comment_id) references comment (comment_id)
    -- replier_id should reference doctor(id) or patient(id)
);

-- Medication
create table if not exists medication (
    id bigserial primary key,
    name VARCHAR(255) NOT NULL,
    dosage varchar(100),
    form varchar(100),
    route varchar(100),
    frequency varchar(100),
    duration_days int not null,
    active boolean not null default true
);

-- Prescription Medications (Join Table)
create table if not exists prescription_medications (
    prescription_id bigint not null,
    medication_id bigint not null,
    primary key (prescription_id, medication_id),
    constraint fk_prescription foreign key (prescription_id) references prescription (id),
    constraint fk_medication foreign key (medication_id) references medication (id)
);


