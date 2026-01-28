package com.pm.doctorservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorMetricsDTO {
  private Long id;

  private Long doctorId;

  private int prescriptionsCount;

  private int patientsCount;

  private double feedbackScore;

  private LocalDateTime updatedAt;
}
