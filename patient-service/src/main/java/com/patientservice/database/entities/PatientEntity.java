package com.patientservice.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "patient_entity")
public class PatientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  private String email;
  private String phone;
  @Column(name = "insurance_number")
  private String insuranceNumber;
  @Column(name = "registered_date")
  private LocalDate registeredDate;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getInsuranceNumber() {
    return insuranceNumber;
  }

  public void setInsuranceNumber(String insuranceNumber) {
    this.insuranceNumber = insuranceNumber;
  }

  public LocalDate getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(LocalDate registeredDate) {
    this.registeredDate = registeredDate;
  }
}
