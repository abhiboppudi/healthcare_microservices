package com.pm.hospitalservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiagnosticsLabResponseDTO {

  private long labId;

  private long hospitalId;

  private String name;

  private String technicianName;

  private String phone;

  private int type;

  private boolean active;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}
