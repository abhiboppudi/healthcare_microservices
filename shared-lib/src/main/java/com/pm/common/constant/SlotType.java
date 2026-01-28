package com.pm.common.constant;

public enum SlotType {

  OUT_PATIENT(0),
  TELECONSULTATION(1),
  VIDEOCONSULTATION(2);

  private final int code;

  SlotType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

}
