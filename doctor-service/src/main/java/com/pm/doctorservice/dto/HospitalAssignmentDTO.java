package com.pm.doctorservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HospitalAssignmentDTO {
  private Long id;

  private Long doctorId;

  private Long hospitalId;

  private LocalDateTime assignedAt;

  private boolean active;
}
