package com.pm.hospitalservice.dto.response;

import lombok.Data;

@Data
public class DoctorAssignmentResponseDTO {

  private long doctorId;

  private long hospitalId;

  private long timingId;

  private boolean active;

}
