package com.pm.doctorservice.repository;

import com.pm.doctorservice.entity.DoctorFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorFeedbackRepository extends JpaRepository<DoctorFeedback, Long> {
  
  Optional<DoctorFeedback> findByDoctorId(Long doctorId);

}
