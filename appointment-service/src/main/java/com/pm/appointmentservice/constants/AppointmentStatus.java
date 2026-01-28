package com.pm.appointmentservice.constants;

public enum AppointmentStatus {
  PENDING(1),
  CONFIRMED(2),
  CANCELLED(3),
  COMPLETED(4),
  NO_SHOW(5),
  RESCHEDULED(6);

  private final int code;

  AppointmentStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}

