package com.pm.appointmentservice.controller;

import com.pm.appointmentservice.dto.request.AppointmentCancellationRequestDTO;
import com.pm.appointmentservice.dto.request.CreateAppointmentRequestDTO;
import com.pm.appointmentservice.dto.response.AppointmentCancellationResponseDTO;
import com.pm.appointmentservice.dto.response.AppointmentViewDTO;
import com.pm.appointmentservice.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
@Tag(name = "Appointments API V1", description = "Appointment APIs - Version 1")
public class AppointmentControllerV1 {

  private final AppointmentService appointmentService;

  public AppointmentControllerV1(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @DeleteMapping("{id}/cancel")
  @Operation(summary = "Cancel Appointment")
  public ResponseEntity<AppointmentCancellationResponseDTO> cancelAppointment(
      @NotNull @PathVariable("id") Long appointmentId,
      @Validated @RequestBody AppointmentCancellationRequestDTO dto) {

    AppointmentCancellationResponseDTO responseDTO = appointmentService.cancelAppointment(appointmentId, dto);
    return ResponseEntity.ok(responseDTO);

  }

  @PatchMapping("/{id}")
  @Operation(summary = "Update Appointment")
  public ResponseEntity<AppointmentViewDTO> updateAppointment(@NotNull @PathVariable("id") Long appointmentId) {
    //appointmentService.updateAppointment(appointmentId);
    return null;
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get Appointment information by appointment id")
  public ResponseEntity<AppointmentViewDTO> getAppointmentInfo(@NotNull @PathVariable("id") Long appointmentId) {

    AppointmentViewDTO viewDTO = appointmentService.getAppointment(appointmentId);
    return ResponseEntity.ok(viewDTO);

  }

  @PostMapping
  @Operation(summary = "Creates appointment")
  public ResponseEntity<AppointmentViewDTO> createAppointment(
      @Validated @RequestBody CreateAppointmentRequestDTO dto) {

    AppointmentViewDTO viewDTO = appointmentService.createAppointment(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(viewDTO);

  }

}
