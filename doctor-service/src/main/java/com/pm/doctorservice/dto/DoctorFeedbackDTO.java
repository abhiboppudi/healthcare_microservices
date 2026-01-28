package com.pm.doctorservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorFeedbackDTO {

  private Long doctorId;

  private double averageRating;   // 1–5

  private LocalDateTime createdAt;

}
