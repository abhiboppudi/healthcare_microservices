package com.pm.doctorservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class DoctorFeedback {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long feedbackId;

  private Long doctorId;

  private double totalStars;

  private Integer feedbackCount;

  private boolean positive;

  private LocalDateTime createdAt;

}
