package com.pm.doctorservice.constant;

public enum CommentAuthorType {

  PATIENT(1),
  DOCTOR(2);

  int code;

  CommentAuthorType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
