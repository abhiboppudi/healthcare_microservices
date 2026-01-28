package com.pm.hospitalservice.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class HospitalTimingsResponseDTO {
  private long timingId;

  private long hospitalId;

  private int session;

  private int slotIntervalMinutes;

  private LocalTime startTime;

  private LocalTime endTime;

  private List<AppointmentSlotResponseDTO> appointmentSlots;
}

