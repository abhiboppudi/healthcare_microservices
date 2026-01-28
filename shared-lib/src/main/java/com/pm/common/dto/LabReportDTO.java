package com.pm.common.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LabReportDTO {
  private Long id;

  private Long patientId;

  private Long labTestId;     // reference to ordered test

  private LocalDate reportDate;

  private String resultSummary; // human-readable summary

  private String resultUrl;     // optional link to full PDF/report

  private LabReportStatus status;
}

