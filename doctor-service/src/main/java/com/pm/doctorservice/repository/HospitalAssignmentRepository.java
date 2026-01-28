package com.pm.doctorservice.repository;

import com.pm.doctorservice.entity.HospitalAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalAssignmentRepository extends JpaRepository<HospitalAssignment, Long> {
}
