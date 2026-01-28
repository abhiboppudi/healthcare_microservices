package com.pm.doctorservice.config;

import com.pm.common.config.ErrorMessageResolver;
import com.pm.common.exceptions.DuplicateEntityException;
import com.pm.common.exceptions.ErrorResponse;
import com.pm.common.exceptions.NoEntityFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class DoctorServiceExceptionHandler {

  private final ErrorMessageResolver messageResolver;

  @ExceptionHandler(DuplicateEntityException.class)
  public ResponseEntity<?> handleDuplicateException(DuplicateEntityException de) {
    String message = messageResolver.resolve(de.getErrorCode(), de.getArgs());
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorResponse(de.getErrorCode().getCode(), message, de.getServiceName()));
  }

  @ExceptionHandler(NoEntityFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoEntityFoundException(NoEntityFoundException ne) {
    String resolvedMessage = messageResolver.resolve(ne.getErrorCode(), ne.getArgs());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(ne.getErrorCode().getCode(), resolvedMessage, ne.getServiceName()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String,Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

    Map<String,String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .collect(Collectors.toMap(
            FieldError::getField,
            fe -> Optional.ofNullable(fe.getDefaultMessage()).orElse("Validation error"),
            (msg1, msg2) -> msg1 //Keep first if duplicate
        ));

    Map<String,Object> response = new LinkedHashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Validation failed");
    response.put("fieldErrors", errors);

    return ResponseEntity.badRequest().body(response);
  }

}
