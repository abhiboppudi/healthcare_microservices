package com.patientservice.request.dto;

import com.patientservice.validation.CreateGroup;
import com.patientservice.validation.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Schema(description = "Data transfer object representing patient details")
public class PatientDTO {

  @Null(groups = CreateGroup.class, message = "ID should not be passed while creating a patient")
  @NotNull(groups = UpdateGroup.class, message = "ID is required")
  @Schema(description = "Unique identifier of the patient", example = "123")
  private Long id;

  @NotBlank(message = "First Name is required")
  @Schema(description = "First name of the patient", example = "John")
  private String firstName;

  @NotBlank(message = "Last Name is required")
  @Schema(description = "Last name of the patient", example = "Miller")
  private String lastName;

  @NotBlank(message = "email is required")
  @Schema(description = "email address of the patient", example = "john.miller@example.com")
  private String email;

  @NotBlank(message = "Phone number is required")
  @Pattern(regexp = "^[0-9]{10}$", message = "phone number must be ten digits")
  @Schema(description = "Mobile number of the patient, typically 10 digits", example = "9848012345")
  private String phone;

  @NotBlank
  @Schema(description = "Insurance number of the patient", example = "INS0123")
  private String insuranceNumber;

  @NotNull(message = "Date is required")
  @PastOrPresent
  @Schema(description = "Date when patient is registered, cannot be future date", example = "2025-10-10")
  private LocalDate registeredDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public @NotBlank(message = "First Name is required") String getFirstName() {
    return firstName;
  }

  public void setFirstName(@NotBlank(message = "First Name is required") String firstName) {
    this.firstName = firstName;
  }

  public @NotBlank(message = "Last Name is required") String getLastName() {
    return lastName;
  }

  public void setLastName(@NotBlank(message = "Last Name is required") String lastName) {
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
