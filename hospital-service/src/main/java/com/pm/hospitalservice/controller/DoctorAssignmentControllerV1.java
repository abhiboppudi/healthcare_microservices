package com.pm.hospitalservice.controller;

import com.pm.hospitalservice.dto.response.DoctorAssignmentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "api-doctor-assignment-v1", description = "API to manage doctor associations " +
    "with hospitals and their session assignments. Ensures doctors are mapped to hospital timings.")
@RestController
@RequestMapping("/api/v1/doctor-assignment")
public class DoctorAssignmentControllerV1 {

//   Should be written as an event listener, when doctor accepts invite.
//  //Change response type, new table to store doctor-hospital mapping
//  @PostMapping("/hospital/{id}/doctor")
//  @Operation(description = "Assigns doctor to hospital")
//  public ResponseEntity<DoctorAssignmentResponseDTO> assignDoctorToHospital(@PathVariable("id") long hospitalId,
//                                                                            @RequestBody
//                                                                            DoctorAssignmentRequestDTO requestDTO) {
//
//    return null;
//  }

//This should be in doctor service
//  @GetMapping("/hospital/{id}/doctors")
//  @Operation(description = "lists all doctors available in the hospital")
//  public ResponseEntity<List<DoctorAssignmentResponseDTO>> getAllDoctorsInHospital()

  @PostMapping("/hospital/{id}/doctor/{doctorId}/assignments")
  @Operation(description = "Add consultation timings for a doctor")
  public ResponseEntity<DoctorAssignmentResponseDTO> assignTimings(@PathVariable("id") long hospitalId,
                                                                   @PathVariable("doctorId") long doctorId) {

    return null;
  }

  @GetMapping("/hospital/{id}/doctor/{doctorId}/assignments")
  @Operation(description = "Find consultation timings for doctor")
  public ResponseEntity<DoctorAssignmentResponseDTO> retreiveConsultationTimings(@PathVariable("id") long hospitalId,
                                                                                 @PathVariable("doctorId")
                                                                                 long doctorId) {

    return null;
  }

}
