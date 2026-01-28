package com.pm.hospitalservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "HospitalAdministratorResponse")
public class HospitalAdministratorResponseDTO {
  private long adminId;

  private String firstName;

  private String lastName;

  private long hospitalId;

  private boolean active;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
