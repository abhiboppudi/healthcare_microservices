package com.pm.hospitalservice.entity.keys;

import jakarta.persistence.Embeddable;

@Embeddable
public class DoctorAssignmentId {
  private long doctorId;

  private long hospitalId;

}
