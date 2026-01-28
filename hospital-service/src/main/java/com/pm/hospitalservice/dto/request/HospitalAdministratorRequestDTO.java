package com.pm.hospitalservice.dto.request;

import lombok.Data;

@Data
public class HospitalAdministratorRequestDTO {
  private String firstName;

  private String lastName;

  private long hospitalId;
}