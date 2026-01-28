package com.pm.common.exceptions;

import com.pm.common.constant.BaseErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

  private final String serviceName;

  private final BaseErrorCode errorCode;

  private final Object[] args;

  public BaseException(String serviceName, BaseErrorCode errorCode, Object... args) {
    super(errorCode.getCode());
    this.serviceName = serviceName;
    this.errorCode = errorCode;
    this.args = args;
  }

}
