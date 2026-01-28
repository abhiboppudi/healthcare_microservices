# Healthcare Management System

## Overview
The Healthcare Management System is a modular platform designed to streamline the outpatient journey.  
It provides patients, doctors, hospitals, and administrators with a unified interface to manage appointments, prescriptions, diagnostics, and billing.  
The system is built as a collection of microservices, all maintained under a single repository.

## Vision
- Simplify healthcare workflows with a single digital platform.
- Empower patients to manage their healthcare journey end-to-end.
- Enable doctors and hospitals to coordinate resources and availability.
- Ensure transparency in billing and compliance with healthcare regulations.

## Architecture
This project follows a microservices architecture. Each service is maintained in its own module for modularity and scalability:

- Patient Service – registration, profile management, appointments, feedback  
- Doctor Service – availability, prescriptions, diagnostics, feedback  
- Hospital Service – resource scheduling, doctor associations, pharmacy, diagnostics  
- Appointment Service – booking, rescheduling, analytics, conflict prevention  
- Billing Service – invoices, payment tracking, refunds  

Each service communicates via APIs and integrates with shared infrastructure (Kafka, Redis, databases, observability stack).

## Getting Started

### Prerequisites
- Java 21  
- Docker (either CLI or Docker Desktop GUI)  
- IntelliJ IDEA or Eclipse (for local development)  

Quick start: Docker container files are stored in the `docker_containers` folder for rapid setup.

### Setup

#### Docker containers
1. You can find **Postgres, Kafka, Confluent Schema Registry** docker compose files under the `docker_containers` folder.  
2. Navigate to the respective folder and run `docker compose up` to start, and `docker compose down` to stop the containers.

#### Run Services Individually
1. Clone the repository and navigate to the service you want to run.  
2. Build and run the service using Maven Wrapper:  
   - Example: `cd patient-service`  
   - Then: `./mvnw spring-boot:run`  
3. Repeat for each service module.
