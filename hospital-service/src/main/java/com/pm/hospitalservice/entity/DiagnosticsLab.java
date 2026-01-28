package com.pm.hospitalservice.entity;

import com.pm.common.constant.LabTestType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class DiagnosticsLab {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long labId;

  private String name;

  private String technicianName;

  private String phone;

  @ManyToOne
  @JoinColumn(name = "hospital_id", referencedColumnName = "hospitalId")
  private Hospital hospital;

  private LabTestType type;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private boolean active;
}
