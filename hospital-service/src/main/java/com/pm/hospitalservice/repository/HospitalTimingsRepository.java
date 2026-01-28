package com.pm.hospitalservice.repository;

import com.pm.hospitalservice.entity.HospitalTimings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalTimingsRepository extends JpaRepository<HospitalTimings, Long> {

  @Query("SELECT ht FROM HospitalTimings ht WHERE ht.hospital.hospitalId=:hospitalId")
  List<HospitalTimings> findByHospital(@Param("hospitalId") long hospitalId);

}
