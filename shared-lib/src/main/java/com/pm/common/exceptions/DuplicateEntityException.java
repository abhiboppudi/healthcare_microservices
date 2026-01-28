package com.pm.common.exceptions;

import com.pm.common.constant.BaseErrorCode;

public class DuplicateEntityException extends BaseException {

  public DuplicateEntityException(String serviceName, BaseErrorCode errorCode, Object... args) {
    super(serviceName, errorCode, args);
  }

}
