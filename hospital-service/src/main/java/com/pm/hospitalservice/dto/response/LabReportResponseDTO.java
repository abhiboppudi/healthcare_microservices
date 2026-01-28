package com.pm.hospitalservice.dto.response;

import lombok.Data;

@Data
public class LabReportResponseDTO {
  private long labReportId;

  private long labId;

  private long hospitalId;

  private long patientId;

  private byte[] reportFile;

}
