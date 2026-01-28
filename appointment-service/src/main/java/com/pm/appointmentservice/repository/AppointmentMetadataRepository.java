package com.pm.appointmentservice.repository;

import com.pm.appointmentservice.entity.AppointmentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentMetadataRepository extends JpaRepository<AppointmentMetadata, Long> {
}
