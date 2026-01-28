package com.patientservice.exception.handlers;

import com.patientservice.exception.NoPatientFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PatientExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(NoPatientFoundException.class)
  public ResponseEntity<?> handleNoPatientFound(NoPatientFoundException ne) {
    return ResponseEntity.badRequest().body(Map.of("error", ne.getMessage()));
  }

}
