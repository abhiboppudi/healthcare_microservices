package com.patientservice.exception;

public class NoPatientFoundException extends RuntimeException {

  public NoPatientFoundException(String msg) {
    super(msg);
  }

}
