package com.pm.hospitalservice.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentSlotRequestDTO {
  private long hospitalId;

  private long doctorId;

  private LocalDate appointmentDate;

  private LocalTime startTime;

  private long patientId;

  private long timingId;

  private int slotType;             // enum ordinal
}
