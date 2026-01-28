-- Hospitals
insert into hospital (hospital_id, name, city, state, active, created_at)
values
  (1, 'Apollo Hospital', 'Hyderabad', 'TG', true, now()),
  (2, 'Care Hospital', 'Hyderabad', 'TG', true, now())
ON CONFLICT (hospital_id) DO NOTHING;

-- Hospital Administrators
insert into hospital_administrator (admin_id, first_name, last_name, gender, hospital_id, active)
values
  (101, 'Ravi', 'Kumar', 'MALE', 1, true),
  (102, 'Sneha', 'Reddy', 'FEMALE', 2, true)
ON CONFLICT (admin_id) DO NOTHING;

-- Hospital Timings (session as ordinal)
insert into hospital_timings (timing_id, hospital_id, session, slot_interval, start_time, end_time)
values
  (201, 1, 0, 15, '09:00', '12:00'),   -- Morning
  (202, 1, 1, 15, '14:00', '18:00'),   -- Afternoon
  (203, 2, 2, 15, '16:00', '20:00')    -- Evening
ON CONFLICT (timing_id) DO NOTHING;

-- Doctor Assignments
insert into doctor_assignment (doctor_id, hospital_id, timing_id, active, created_at, updated_at)
values
  (301, 1, 201, true, now(), null),
  (302, 1, 202, true, now(), null),
  (303, 2, 203, true, now(), null)
ON CONFLICT (doctor_id, hospital_id, timing_id) DO NOTHING;

-- Appointment Slots (slotType as ordinal)
insert into appointment_slot (hospital_id, doctor_id, appointment_date, start_time, patient_id, timing_id, reserved, slot_type)
values
  (1, 301, '2026-01-03', '09:00', 501, 201, true, 0),   -- OPD
  (1, 301, '2026-01-03', '09:15', 502, 201, true, 0),
  (1, 302, '2026-01-03', '14:00', 503, 202, true, 1),   -- Teleconsultation
  (2, 303, '2026-01-03', '16:00', 504, 203, true, 0),
  (2, 303, '2026-01-03', '16:15', 505, 203, false, 0)
ON CONFLICT DO NOTHING;

-- Diagnostics Labs (type as ordinal)
insert into diagnostics_lab (lab_id, name, technician_name, phone, hospital_id, type, active)
values
  (401, 'Pathology Lab', 'Dr. Suresh', '9876543210', 1, 0, true),
  (402, 'Radiology Lab', 'Dr. Anita', '9876543211', 2, 1, true)
ON CONFLICT (lab_id) DO NOTHING;

-- Lab Reports
insert into lab_report (lab_report_id, lab_id, hospital_id, patient_id, report_file, mime_type, created_at)
values
  (601, 401, 1, 501, decode('25504446', 'hex'), 'application/pdf', now()),  -- "%PDF" header
  (602, 402, 2, 502, decode('25504446', 'hex'), 'application/pdf', now())
ON CONFLICT (lab_report_id) DO NOTHING;

-- Billing (billingStatus as ordinal)
insert into billing (billing_id, appointment_id, hospital_id, patient_id, bill_amount, currency, billing_status, updated_at)
values
  (701, 1001, 1, 10001, 1500.00, 'INR', 0, now()),   -- PAID
  (702, 1002, 2, 10002, 2000.00, 'INR', 1, now())    -- UNPAID
ON CONFLICT (billing_id) DO NOTHING;

insert into doctor_invitation
    (invitation_id, hospital_id, doctor_id, status, created_at, responded_at, created_by, expiryDate)
values
    (1, 101, 201, 'PENDING',  '2026-01-05 09:00:00', null, 1001, '2026-02-05 09:00:00'),
    (2, 101, 202, 'ACCEPTED', '2026-01-04 14:30:00', '2026-01-04 15:00:00', 1002, '2026-02-05 09:00:00'),
    (3, 102, 203, 'REJECTED', '2026-01-03 11:15:00', '2026-01-03 12:00:00', 1003, '2026-02-05 09:00:00')
ON CONFLICT (invitation_id) DO NOTHING;