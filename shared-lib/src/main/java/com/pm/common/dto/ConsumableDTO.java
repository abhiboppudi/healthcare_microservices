package com.pm.common.dto;

import lombok.Data;

@Data
public class ConsumableDTO {
  private Long id;

  private String name;        // e.g., "Syringe"

  private String category;    // e.g., "Injection Supplies"

  private String unit;        // e.g., "piece", "pack"

  // Ownership note: stock fields are pharmacy-owned; include if you plan to share them
  private Integer stockQuantity; // optional

}
