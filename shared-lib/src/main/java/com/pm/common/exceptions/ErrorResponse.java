package com.pm.common.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
  private final String errorCode;

  private final String message;

  private final String serviceName;

  private final LocalDateTime timestamp;

  public ErrorResponse(String errorCode, String message, String serviceName) {
    this.errorCode = errorCode;
    this.message = message;
    this.serviceName = serviceName;
    this.timestamp = LocalDateTime.now();
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getMessage() {
    return message;
  }

  public String getServiceName() {
    return serviceName;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
