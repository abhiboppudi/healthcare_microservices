package com.pm.appointmentservice.constants;

public enum CancelledByType {
  PATIENT(1),
  DOCTOR(2),
  ADMIN(3);

  private final int code;


  CancelledByType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static CancelledByType fromCode(int code) {
    for (CancelledByType type : values()) {
      if (type.code == code) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown CancelledByType code: " + code);
  }
}

