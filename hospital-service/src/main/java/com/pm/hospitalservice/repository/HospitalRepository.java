package com.pm.hospitalservice.repository;

import com.pm.hospitalservice.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

  boolean existsByName(String name);

  @Query(" SELECT h.active FROM Hospital h WHERE h.hospitalId=:hospitalId")
  Optional<Boolean> isHospitalExistsAndActive(@Param("hospitalId") long hospitalId);

  boolean existsByHospitalIdAndEmail(long hospitalId, String email);

  @Modifying
  @Query("UPDATE Hospital h SET h.active = CASE WHEN h.active = true THEN false ELSE true END " +
      " WHERE h.hospitalId = :hospitalId")
  void toggleStatus(@Param("hospitalId") long hospitalId);

}
