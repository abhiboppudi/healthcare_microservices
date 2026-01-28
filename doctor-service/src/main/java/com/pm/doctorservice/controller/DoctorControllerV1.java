package com.pm.doctorservice.controller;

import com.pm.common.dto.DoctorInvitationDTO;
import com.pm.common.dto.InvitationResponseDTO;
import com.pm.doctorservice.dto.DoctorDTO;
import com.pm.doctorservice.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@Tag(name = "doctors-api-v1", description = "Doctors API - version 1")
public class DoctorControllerV1 {

  private final DoctorService doctorService;

  public DoctorControllerV1(DoctorService doctorService) {
    this.doctorService = doctorService;
  }

  @GetMapping("/getAllActiveDoctors")
  @Operation(description = "API to get all the active doctors profile")
  public ResponseEntity<?> getAllActiveDoctors(){
      List<DoctorDTO> dto = doctorService.getAllActiveDoctors();
      return ResponseEntity.ok(dto);
  }

  @GetMapping("/{id}")
  @Operation(description = "API to get doctor profile")
  public ResponseEntity<?> getDoctorProfile(@NotNull @PathVariable("id") long doctorId) {
    DoctorDTO dto = doctorService.getDoctor(doctorId);
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  @Operation(description = "API to create a doctor, Doctor Metrics should be created in a seperate API")
  public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(dto));
  }

  @PutMapping("/{id}")
  @Operation(description = "Updates Doctor profile")
  public ResponseEntity<DoctorDTO> updateDoctorProfile(@PathVariable("id") Long id, @RequestBody DoctorDTO dto) {
    return ResponseEntity.ok(doctorService.updateDoctorProfile(id, dto));
  }

  @GetMapping("/hospital/{hospitalId}")
  @Operation(description = "Find all Doctors in a given hospital")
  public ResponseEntity<List<DoctorDTO>> listDoctorsByHospital(@PathVariable("hospitalId") Long hospitalId) {
    return ResponseEntity.ok(doctorService.getDoctorsByHospital(hospitalId));
  }

  @GetMapping("/{doctorId}/invites")
  @Operation(description = "Lists invitation from hospitals")
  public ResponseEntity<List<DoctorInvitationDTO>> listHospitalInvite(@PathVariable("doctorId") long doctorId){
    List<DoctorInvitationDTO> invites = doctorService.fetchHospitalInvites(doctorId);
    return ResponseEntity.ok(invites);
  }

  @PostMapping("/invite/update")
  @Operation(description = "Doctor accepts or rejects an invitation sent by hospital")
  public ResponseEntity<Void> updateStatus(@Valid @RequestBody InvitationResponseDTO invitationResponse){
    doctorService.updateInvite(invitationResponse);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
