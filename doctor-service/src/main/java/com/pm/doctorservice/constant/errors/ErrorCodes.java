package com.pm.doctorservice.constant.errors;

import com.pm.common.constant.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes implements BaseErrorCode {

  DOC_DUPLICATE("DC001"),

  DOC_NOT_FOUND("DC002"),

  COMMENT_DUPLICATE("CM001"),

  COMMENT_NOT_FOUND("CM002"),

  FEEDBACK_NOT_FOUND("FB001");

  private final String errorCode;

  @Override
  public String getCode() {
    return errorCode;
  }
}
