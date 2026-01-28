package com.pm.hospitalservice.entity.keys;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
public class AppointmentSlotId {

  private long hospitalId;

  private long doctorId;

  private long patientId;

  private LocalDate appointmentDate;

  private LocalTime startTime;

}
