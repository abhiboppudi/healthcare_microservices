package com.pm.appointmentservice.repository;

import com.pm.appointmentservice.entity.AppointmentFollowup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentFollowupRepository extends JpaRepository<AppointmentFollowup, Long> {
}
