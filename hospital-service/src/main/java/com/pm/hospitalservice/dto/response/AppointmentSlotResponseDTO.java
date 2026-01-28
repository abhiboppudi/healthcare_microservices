package com.pm.hospitalservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Schema(name = "AppointmentSlots")
public class AppointmentSlotResponseDTO {
  private long hospitalId;

  private long doctorId;

  private LocalDate appointmentDate;

  private LocalTime startTime;

  private long patientId;

  private long timingId;

  private boolean reserved;

  private int slotType;
}
