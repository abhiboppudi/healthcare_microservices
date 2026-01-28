package com.pm.hospitalservice.dto.request;

import lombok.Data;

@Data
public class HospitalRequestDTO {

  private String name;

  private String address;

  private String city;

  private String state;

  private String zipcode;

  private String phone;

  private String email;
}
