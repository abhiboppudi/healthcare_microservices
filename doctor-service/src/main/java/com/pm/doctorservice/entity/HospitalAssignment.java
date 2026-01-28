package com.pm.doctorservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class HospitalAssignment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  //private long doctorId;

  private long hospitalId;

  private LocalDateTime assignedAt;

  private boolean active;

  @OneToOne
  @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
  private Doctor doctor;

}
