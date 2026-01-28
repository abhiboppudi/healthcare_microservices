package com.pm.appointmentservice.exception;

public class DuplicateAppointmentException extends RuntimeException {

  public DuplicateAppointmentException(String msg) {
    super(msg);
  }
}
