-- Doctor
insert into doctor (
    doctor_id, first_name, last_name, gender, email_id, phone_number, license_number,
    active_status, specialization, years_of_experience, qualifications, consultation_fees,
    created_date, created_by, last_updated_date, last_updated_by
)
values
(501, 'Arjun', 'Rao', 'MALE', 'arjun.rao@hospital.com', '9876543210', 'LIC501',
 true, 'Cardiology', 15, 'MBBS, MD', 1500.00, current_timestamp, 'system', current_timestamp, 'system'),
(502, 'Meera', 'Iyer', 'FEMALE', 'meera.iyer@hospital.com', '9876543211', 'LIC502',
 true, 'Neurology', 12, 'MBBS, DM', 2000.00, current_timestamp, 'system', current_timestamp, 'system')
ON CONFLICT (doctor_id) DO NOTHING;

-- Doctor Metrics
insert into doctor_metrics (
    metrics_id, doctor_id, average_stars, total_recommendations, total_positive_feedback, total_negative_feedback
)
values
(1, 501, 4.50, 100, 90, 10),
(2, 502, 4.70, 85, 80, 5)
ON CONFLICT (metrics_id) DO NOTHING;


-- Hospital Assignment
insert into hospital_assignment (id, doctor_id, hospital_id, active)
values
(1, 501, 3001, true),
(2, 502, 3002, true)
ON CONFLICT (id) DO NOTHING;

-- Doctor Feedback
insert into doctor_feedback (
    feedback_id, doctor_id, total_stars, feedback_count, positive, created_at
)
values
(1, 501, 5, 1, true, current_timestamp),
(2, 502, 4, 2, true, current_timestamp)
ON CONFLICT (feedback_id) DO NOTHING;

-- Prescription
insert into prescription (id, doctor_id, patient_id, appointment_id, hospital_id, issued_date, valid_until, status, notes)
values
(1001, 501, 10001, 2001, 3001, '2025-12-29', '2026-01-05', 'ISSUED', 'Take after meals'),
(1002, 502, 10002, 2002, 3002, '2025-12-28', '2026-01-04', 'ISSUED', 'Hydrate well')
ON CONFLICT (id) DO NOTHING;

-- Comment
insert into comment (comment_id, doctor_id, author_id, content, star_count, created_at, active)
values
(1, 501, 10001, 'Good Doctor', 5, current_timestamp, true),
(2, 501, 10001, 'Good Doctor', 4, current_timestamp, true)
ON CONFLICT (comment_id) DO NOTHING;

-- Comment Reply
insert into comment_reply (id, comment_id, replier_id, content, created_at, active)
values
(1, 1, 10001, 'Thank you', current_timestamp, true)
ON CONFLICT (id) DO NOTHING;

-- Medication
insert into medication (id, name, dosage, form, route, frequency, duration_days, active)
values
(1, 'Amoxicillin', '500mg', 'Tablet', 'Oral', 'Twice a day', 7, true),
(2, 'Paracetamol', '650mg', 'Tablet', 'Oral', 'Thrice a day', 5, true),
(3, 'Insulin', '10 units', 'Injection', 'Subcutaneous', 'Once a day', 30, true)
ON CONFLICT (id) DO NOTHING;

-- Prescription Medications
insert into prescription_medications (prescription_id, medication_id)
values
(1001, 1),
(1001, 2),
(1002, 3)
ON CONFLICT DO NOTHING;
