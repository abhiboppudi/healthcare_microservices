-- ============================================================
-- Appointment records with existence check
-- ============================================================
insert into appointment (appointment_id, patient_id, doctor_id, hospital_id, appointment_time, status, type)
select 1, 103, 203, 303, '2025-12-16 10:00:00', 'CONFIRMED', 'IN_PERSON'
where not exists (select 1 from appointment where appointment_id = 1);

insert into appointment (appointment_id, patient_id, doctor_id, hospital_id, appointment_time, status, type)
select 2, 104, 204, 304, '2025-12-17 11:00:00', 'PENDING', 'VIDEO'
where not exists (select 1 from appointment where appointment_id = 2);

-- ============================================================
-- Appointment followups
-- ============================================================
insert into appointment_followup (appointment_id, followup_required, followup_date, followup_reason, followup_notes, recommended_by, status, created_at, updated_at)
select 1, true, '2026-01-10 10:00:00', 'Routine check-up', 'Patient to return for vitamin D test', 203, 'ACTIVE', now(), now()
where not exists (select 1 from appointment_followup where appointment_id = 1);

insert into appointment_followup (appointment_id, followup_required, followup_date, followup_reason, followup_notes, recommended_by, status, created_at, updated_at)
select 2, true, '2026-02-05 11:00:00', 'Post-treatment review', 'Check recovery progress', 206, 'COMPLETED', now(), now()
where not exists (select 1 from appointment_followup where appointment_id = 2);

-- ============================================================
-- Appointment details
-- ============================================================
insert into appointment_details (followup_id, reason, patient_notes, doctor_notes, source)
select 1, 'Initial consultation', 'Fatigue and weakness', 'Vitamin D deficiency diagnosed', 'MOBILE_APP'
where not exists (select 1 from appointment_details where followup_id = 1);

insert into appointment_details (followup_id, reason, patient_notes, doctor_notes, source)
select 2, 'Routine check-up', 'Feeling better', 'Condition improved, continue supplements', 'WEB'
where not exists (select 1 from appointment_details where followup_id = 2);

-- ============================================================
-- Appointment payments
-- ============================================================
insert into appointment_payment (followup_id, payment_required, payment_completed, payment_id, consultation_fee)
select 1, true, true, 50001, 500.0
where not exists (select 1 from appointment_payment where followup_id = 1);

insert into appointment_payment (followup_id, payment_required, payment_completed, payment_id, consultation_fee)
select 2, true, false, 50002, 700.0
where not exists (select 1 from appointment_payment where followup_id = 2);

-- ============================================================
-- Appointment metadata
-- ============================================================
insert into appointment_metadata (appointment_id, meta_key, meta_value, created_at)
select 1, 'priority', 'HIGH', now()
where not exists (select 1 from appointment_metadata where appointment_id = 1 and meta_key = 'priority');

insert into appointment_metadata (appointment_id, meta_key, meta_value, created_at)
select 2, 'insurance', 'Policy#12345', now()
where not exists (select 1 from appointment_metadata where appointment_id = 2 and meta_key = 'insurance');
