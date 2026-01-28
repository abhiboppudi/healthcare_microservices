package com.pm.common.dto;

import lombok.Data;

@Data
public class MedicationDTO {
  private Long id;

  private String name;       // e.g., "Amoxicillin"

  private String dosage;     // e.g., "500mg"

  private String frequency;  // e.g., "Twice a day"

  private Integer durationDays; // e.g., 7

  private String notes;      // optional per-medication notes
}
