package com.pm.appointmentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "appointment_payment")
public class AppointmentPayment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentRecordId;

  @Column(name = "followup_id")
  private Long followupId;

  private Boolean paymentRequired;

  private Boolean paymentCompleted;

  private Long paymentId; // Reference to Billing Service

  private Double consultationFee;
}
