package com.pm.doctorservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor_metrics")
public class DoctorMetrics {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long metricsId;

  @Column(nullable = false)
  private long doctorId;

  private double averageStars;

  private int totalRecommendations;

  private int totalPositiveFeedback;

  private int totalNegativeFeedback;

}
