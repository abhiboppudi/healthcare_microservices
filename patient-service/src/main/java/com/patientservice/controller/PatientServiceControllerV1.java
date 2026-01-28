package com.patientservice.controller;

import com.patientservice.request.dto.PatientDTO;
import com.patientservice.service.PatientService;
import com.patientservice.validation.CreateGroup;
import com.patientservice.validation.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name = "Patients V1", description = "Patient APIs - Version 1")
public class PatientServiceControllerV1 {

  private final PatientService patientService;

  public PatientServiceControllerV1(PatientService patientService) {
    this.patientService = patientService;
  }

  @Operation(summary = "Get patient by ID (v1)")
  @GetMapping("/{id}")
  public ResponseEntity<PatientDTO> getPatient(@NotNull @PathVariable("id") long id) {
    PatientDTO patient = patientService.getPatient(id);
    return patient != null ? ResponseEntity.ok().body(patient) : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Get all patients (v1)")
  @GetMapping("/all")
  public ResponseEntity<List<PatientDTO>> getAllPatients() {
    List<PatientDTO> patients = patientService.findAllPatients();
    return patients != null ? ResponseEntity.ok().body(patients) : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Create Patient (v1) registers a new patient into the healthcare application and must be invoked only once per patient.")
  @PostMapping
  public ResponseEntity<PatientDTO> createPatient(@Validated(CreateGroup.class) @RequestBody PatientDTO patient) {
    patient = patientService.createPatient(patient);
    return ResponseEntity.status(HttpStatus.CREATED).body(patient);
  }

  @Operation(summary = "Update patient (v1) updates patient profile")
  @PutMapping
  public ResponseEntity<PatientDTO> updatePatient(@Validated(UpdateGroup.class) @RequestBody PatientDTO patient) {
    patient = patientService.updatePatient(patient);
    return ResponseEntity.status(HttpStatus.OK).body(patient);
  }

  @Operation(summary = "Delete Patient (v1) removes a patient from the healthcare application, preventing any further access to the system.")
  @DeleteMapping("/{id}")
  public ResponseEntity<PatientDTO> deletePatient(@PathVariable long id) {

    return null;
  }

}