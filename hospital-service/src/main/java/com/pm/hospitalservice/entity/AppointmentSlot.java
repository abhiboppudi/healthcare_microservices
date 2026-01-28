package com.pm.hospitalservice.entity;

import com.pm.common.constant.SlotType;
import com.pm.hospitalservice.entity.keys.AppointmentSlotId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class AppointmentSlot {

  @EmbeddedId
  private AppointmentSlotId id;

  //Foreign key to Hospital Timings
  @ManyToOne
  @JoinColumn(name = "timing_id", referencedColumnName = "timingId")
  private HospitalTimings timing;
  //private long timingId;

  private boolean reserved;

  private SlotType slotType;

}
