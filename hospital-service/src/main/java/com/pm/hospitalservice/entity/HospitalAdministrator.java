package com.pm.hospitalservice.entity;

import com.pm.common.constant.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class HospitalAdministrator {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long adminId;

  private String firstName;

  private String lastName;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  //Link Hospital
  @ManyToOne
  @JoinColumn(name = "hospital_id", referencedColumnName = "hospitalId")
  Hospital hospital;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private boolean active;

}
