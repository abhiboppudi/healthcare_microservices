package com.pm.appointmentservice.constants;

public enum AppointmentType {
  IN_PERSON(1),
  VIDEO(2),
  PHONE(3);

  private final int code;

  AppointmentType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
