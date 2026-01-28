package com.pm.hospitalservice.constants;

import com.pm.common.constant.BaseErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCodes implements BaseErrorCode {

  DUPLICATE_HOSPITAL("HL001"),

  HOSPITAL_NOT_FOUND("HL002"),

  DUPLICATE_ADMINISTRATOR("ADM001"),

  ADMIN_NOT_FOUND("ADM002"),

  DUPLICATE_INVITE("INV001"),

  INVITE_ACCEPTED("INV002"),

  INVITE_NOT_FOUND("INV003");

  private final String errorCode;

  @Override
  public String getCode() {
    return errorCode;
  }

}
