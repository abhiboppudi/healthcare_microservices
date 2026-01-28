package com.pm.appointmentservice.entity;

import com.pm.appointmentservice.constants.CancelledByType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointment_cancellation")
public class AppointmentCancellation {

  @Id
  @Column(name = "appointment_id")
  private Long appointmentId;

  private LocalDateTime cancelledAt;

  private Long cancelledBy; // patientId or doctorId

  @Enumerated(EnumType.STRING)
  private CancelledByType cancelledByType;

  @Column(columnDefinition = "TEXT")
  private String cancellationReason;
}
