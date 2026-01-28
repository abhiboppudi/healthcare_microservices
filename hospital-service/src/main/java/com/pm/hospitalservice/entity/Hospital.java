package com.pm.hospitalservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
public class Hospital {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long hospitalId;

  private String name;

  private String address;

  private String city;

  private String state;

  private String zipcode;

  private String phone;

  private String email;

  private boolean active;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "hospital")
  private List<HospitalAdministrator> administrators;

}
