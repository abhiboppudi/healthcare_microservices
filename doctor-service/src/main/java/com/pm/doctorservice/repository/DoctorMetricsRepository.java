package com.pm.doctorservice.repository;

import com.pm.doctorservice.entity.DoctorMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorMetricsRepository extends JpaRepository<DoctorMetrics, Long> {
}
