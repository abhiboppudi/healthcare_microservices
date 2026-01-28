
-- Create patient table in PostgreSQL
create table if not exists patient_entity (
    id bigserial primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(150) unique not null,
    phone varchar(15) not null,
    insurance_number varchar(50) not null,
    registered_date date not null
);
