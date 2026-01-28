package com.pm.hospitalservice.dto.request;

import lombok.Data;

@Data
public class DiagnosticsLabRequestDTO {

  private long hospitalId;

  private String name;

  private String technicianName;

  private String phone;

  private int type;                 // enum ordinal

}