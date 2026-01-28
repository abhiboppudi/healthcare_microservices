package com.pm.hospitalservice.dto.request;

import lombok.Data;

@Data
public class DoctorAssignmentRequestDTO {
  private long doctorId;

  private long hospitalId;

  private long timingId;
}