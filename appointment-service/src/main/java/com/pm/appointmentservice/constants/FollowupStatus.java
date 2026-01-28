package com.pm.appointmentservice.constants;

public enum FollowupStatus {

  ACTIVE(1),
  COMPLETED(2),
  CANCELLED(3);

  int code;

  FollowupStatus(int code) {
    this.code = code;
  }

}
