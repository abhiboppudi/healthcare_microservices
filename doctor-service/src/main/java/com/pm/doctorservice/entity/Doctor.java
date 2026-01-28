package com.pm.doctorservice.entity;

import com.pm.common.constant.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long doctorId;

  private String firstName;

  private String lastName;

  @Enumerated(EnumType.STRING)
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

  @OneToOne(mappedBy = "doctor")
  private HospitalAssignment hospitalAssignment;

}
