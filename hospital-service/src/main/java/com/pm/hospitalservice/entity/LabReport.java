package com.pm.hospitalservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class LabReport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long labReportId;

  @ManyToOne
  @JoinColumn(name = "lab_id", referencedColumnName = "labId")
  private DiagnosticsLab lab;

  private long hospitalId;

  private long patientId;

  @Lob
  private byte reportFile;

  //"application/pdf"
  private String mimeType;

  private LocalDateTime createdAt;
  
}
