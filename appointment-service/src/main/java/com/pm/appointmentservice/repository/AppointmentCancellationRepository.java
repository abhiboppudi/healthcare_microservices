package com.pm.appointmentservice.repository;

import com.pm.appointmentservice.entity.AppointmentCancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentCancellationRepository extends JpaRepository<AppointmentCancellation, Long> {
}
