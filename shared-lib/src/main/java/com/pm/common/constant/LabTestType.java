package com.pm.common.constant;

public enum LabTestType {

  URINE_TEST(0),
  BLOOD_TEST(1),
  IMAGING(2),
  RADIOLOGY(3),
  XRAY(4),
  MRI(5);

  final int type;

  LabTestType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }
}
