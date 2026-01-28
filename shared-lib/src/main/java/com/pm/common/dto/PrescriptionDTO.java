package com.pm.common.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PrescriptionDTO {
  private Long id;

  private Long doctorId;

  private Long patientId;

  private Long appointmentId;

  private Long hospitalId;

  private String hospitalName; // optional snapshot for readability

  private LocalDate issuedDate;

  private LocalDate validUntil;

  private List<MedicationDTO> medications;

  private String notes; // overall instructions

  private PrescriptionStatus status;
}
