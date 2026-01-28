package com.pm.hospitalservice.exception;

import com.pm.common.config.ErrorMessageResolver;
import com.pm.common.exceptions.DuplicateEntityException;
import com.pm.common.exceptions.ErrorResponse;
import com.pm.common.exceptions.NoEntityFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class HospitalServiceExceptionHandler {

  private final ErrorMessageResolver messageResolver;

  @ExceptionHandler
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
}
