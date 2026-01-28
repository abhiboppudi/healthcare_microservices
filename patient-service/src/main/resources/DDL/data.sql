-- Insert sample patients only if that email doesn't already exist
insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'John', 'Doe', 'john.doe@example.com', '1234567890', 'INS001', current_date
where not exists (select 1 from patient_entity where email = 'john.doe@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Jane', 'Smith', 'jane.smith@example.com', '9876543210', 'INS002', current_date
where not exists (select 1 from patient_entity where email = 'jane.smith@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Alice', 'Brown', 'alice.brown@example.com', '5555555555', 'INS003', current_date
where not exists (select 1 from patient_entity where email = 'alice.brown@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Bob', 'Johnson', 'bob.johnson@example.com', '4444444444', 'INS004', current_date
where not exists (select 1 from patient_entity where email = 'bob.johnson@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Charlie', 'Williams', 'charlie.williams@example.com', '3333333333', 'INS005', current_date
where not exists (select 1 from patient_entity where email = 'charlie.williams@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Diana', 'Taylor', 'diana.taylor@example.com', '2222222222', 'INS006', current_date
where not exists (select 1 from patient_entity where email = 'diana.taylor@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Ethan', 'Thomas', 'ethan.thomas@example.com', '1111111111', 'INS007', current_date
where not exists (select 1 from patient_entity where email = 'ethan.thomas@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Fiona', 'Clark', 'fiona.clark@example.com', '6666666666', 'INS008', current_date
where not exists (select 1 from patient_entity where email = 'fiona.clark@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'George', 'Lewis', 'george.lewis@example.com', '7777777777', 'INS009', current_date
where not exists (select 1 from patient_entity where email = 'george.lewis@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Hannah', 'Walker', 'hannah.walker@example.com', '8888888888', 'INS010', current_date
where not exists (select 1 from patient_entity where email = 'hannah.walker@example.com');

insert into patient_entity (first_name, last_name, email, phone, insurance_number, registered_date)
select 'Ian', 'Hall', 'ian.hall@example.com', '9999999999', 'INS011', current_date
where not exists (select 1 from patient_entity where email = 'ian.hall@example.com');
