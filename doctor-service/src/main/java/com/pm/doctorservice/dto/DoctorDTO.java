package com.pm.doctorservice.dto;

import com.pm.common.constant.Gender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorDTO {

  private Long doctorId;

  private String firstName;

  private String lastName;

  private Gender gender;

  private String emailId;

  private String phoneNumber;

  private String licenseNumber;

  private boolean activeStatus;

  private String specialization;

  private int yearsOfExperience;

  private String qualifications;

  private double consultationFees;

  private LocalDateTime createdDate;

  private String createdBy;

  private LocalDateTime lastUpdatedDate;

  private String lastUpdatedBy;
}
