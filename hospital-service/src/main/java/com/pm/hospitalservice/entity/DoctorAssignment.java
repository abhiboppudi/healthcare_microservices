package com.pm.hospitalservice.entity;

import com.pm.hospitalservice.entity.keys.DoctorAssignmentId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class DoctorAssignment {

  @EmbeddedId
  DoctorAssignmentId key;

  @ManyToOne
  @JoinColumn(name = "timing_id", referencedColumnName = "timingId")
  HospitalTimings timing;

  private boolean active;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}


