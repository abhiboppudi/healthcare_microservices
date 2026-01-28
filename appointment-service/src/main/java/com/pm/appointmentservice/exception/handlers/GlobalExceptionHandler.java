package com.pm.appointmentservice.exception.handlers;

import com.pm.appointmentservice.exception.DuplicateAppointmentException;
import com.pm.appointmentservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicateAppointmentException.class)
  public ResponseEntity<?> handleDuplicatePatientCreationException(DuplicateAppointmentException de) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(de.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ne) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ne.getMessage());
  }

}