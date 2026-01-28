package com.pm.common.constant;

public enum BillingStatus {

  PAID(0),
  UN_PAID(1);

  final int status;

  BillingStatus(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
