package com.pm.hospitalservice.repository;

import com.pm.hospitalservice.entity.HospitalAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalAdminRepository extends JpaRepository<HospitalAdministrator, Long> {

  boolean existsByFirstNameAndLastName(String firstName, String lastName);

  @Modifying
  @Query("UPDATE HospitalAdministrator ha SET ha.active = CASE WHEN ha.active=true then false else true END " +
      " WHERE ha.adminId=:adminId ")
  void toggleActiveStatus(@Param("adminId") long adminId);
}
