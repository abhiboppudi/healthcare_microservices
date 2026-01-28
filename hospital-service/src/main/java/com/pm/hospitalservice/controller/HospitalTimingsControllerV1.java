package com.pm.hospitalservice.controller;

import com.pm.hospitalservice.dto.request.HospitalTimingsRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalTimingsResponseDTO;
import com.pm.hospitalservice.service.HospitalTimingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "api-hospital-timing-v1", description = "API defines hospital-wide session timings " +
    "and slot granularity. Assigns and controls timing slots for a hospital")
@RestController
@RequestMapping("/api/v1/hospital-timing")
public class HospitalTimingsControllerV1 {

  private final HospitalTimingService service;

  @PostMapping("/hospital/{id}/timing")
  @Operation(description = "Assign a time period for consultation, ex. 11AM to 2:00PM ")
  public ResponseEntity<HospitalTimingsResponseDTO> assignTimingSession(@PathVariable("id") long hospitalId,
                                                                        @RequestBody
                                                                        HospitalTimingsRequestDTO requestDTO) {
    HospitalTimingsResponseDTO responseDTO = service.addTimings(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @DeleteMapping("/hospital/{id}/doctor/{doctorId}")
  @Operation(description = "remove the consultation time period")
  public ResponseEntity<Void> removeTimingSession(@PathVariable("id") long hospitalId,
                                                  @PathVariable("doctorId") long doctorId) {

    return null;
  }

  @GetMapping("/hospital/{id}/timing")
  @Operation(description = "returns a list of available consultation timings for a given hospital")
  public ResponseEntity<List<HospitalTimingsResponseDTO>> getAvailableTimings(@PathVariable("id") long hospitalId) {
    List<HospitalTimingsResponseDTO> responseDTOs = service.getTimings(hospitalId);
    return ResponseEntity.ok(responseDTOs);
  }

}
