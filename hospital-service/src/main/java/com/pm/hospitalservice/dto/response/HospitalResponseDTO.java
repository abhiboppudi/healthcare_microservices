package com.pm.hospitalservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "HospitalResponse")
public class HospitalResponseDTO {

  private long hospitalId;

  private String name;

  private String address;

  private String city;

  private String zipcode;

  private String email;

  private String phone;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private List<HospitalAdministratorResponseDTO> administrators;
}
