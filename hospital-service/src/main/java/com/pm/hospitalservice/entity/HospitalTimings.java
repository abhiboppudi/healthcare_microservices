package com.pm.hospitalservice.entity;

import com.pm.common.constant.SessionTiming;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Entity
public class HospitalTimings {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long timingId;

  @Enumerated(EnumType.ORDINAL)
  private SessionTiming session;

  private int slotInterval;

  private LocalTime startTime;

  private LocalTime endTime;

  @OneToMany(mappedBy = "timing")
  private List<AppointmentSlot> appointmentSlots;

  @ManyToOne
  @JoinColumn(name = "hospital_id", referencedColumnName = "hospitalId")
  private Hospital hospital;

}
