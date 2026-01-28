package com.pm.common.constant;

public enum SessionTiming {

  MORNING(1),
  AFTERNOON(2),
  EVENING(3);

  final int code;

  SessionTiming(int timing) {
    this.code = timing;
  }

  public int getCode() {
    return code;
  }
}
