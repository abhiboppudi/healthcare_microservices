package com.pm.hospitalservice.dto.request;

import lombok.Data;

@Data
public class LabReportRequestDTO {

  private long hospitalId;

  private long patientId;

  private String mimeType;
}