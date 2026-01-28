package com.pm.hospitalservice.controller;

import com.pm.hospitalservice.dto.request.HospitalRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalResponseDTO;
import com.pm.hospitalservice.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "hospital-api-v1", description = "Hospital API, version 1, Manages hospital identity and lifecycle. " +
    " Handles creation, updates, deactivation, and retrieval of hospital details")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalControllerV1 {

  private final HospitalService hospitalService;

  @GetMapping("/{id}")
  @Operation(description = "Returns Hospital profile by hospital id")
  public ResponseEntity<HospitalResponseDTO> findHospitalById(@PathVariable("id") long hospitalId) {
    HospitalResponseDTO responseDTO = hospitalService.findHospital(hospitalId);
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping
  @Operation(description = "creates hospital entity")
  public ResponseEntity<HospitalResponseDTO> createHospital(@RequestBody HospitalRequestDTO requestDTO) {
    HospitalResponseDTO responseDTO = hospitalService.addNewHospital(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @GetMapping("/active")
  @Operation(description = "Returns list of all active hospitals")
  public ResponseEntity<List<HospitalResponseDTO>> findActiveHospitals() {
    List<HospitalResponseDTO> responseDTOs = hospitalService.findActiveHospitals();
    return ResponseEntity.ok(responseDTOs);
  }

  @PutMapping("/{hospitalId}")
  @Operation(description = "Updates hospital information")
  public ResponseEntity<HospitalRequestDTO> updateHospitalInfo(@PathVariable("hospitalId") long hospitalId,
                                                               @RequestBody HospitalRequestDTO requestDTO) {

    HospitalResponseDTO responseDTO = hospitalService.updateHospitalDetails(hospitalId, requestDTO);
    return null;
  }

  @DeleteMapping("/{hospitalId}/activity/toggle")
  @Operation(description = "change status of hospital to active/inactive")
  public ResponseEntity<Void> toggleHospitalActivity(@PathVariable("hospitalId") long hospitalId) {
    hospitalService.updateHospitalAsInactive(hospitalId);
    return null;
  }

}
