package com.pm.appointmentservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "appointment_followup")
public class AppointmentFollowup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "followup_id")
  private Long followupId;

  @Column(name = "appointment_id")
  private Long appointmentId;

  private Boolean followupRequired;

  private LocalDateTime followupDate;

  @Column(columnDefinition = "TEXT")
  private String followupReason;

  @Column(columnDefinition = "TEXT")
  private String followupNotes;

  private Long recommendedBy;

  @Column(name = "cancelled_at")
  private LocalDateTime cancelledAt;

  @Column(name = "cancellation_reason")
  private String cancellationReason;

  @Column(name = "cancelled_by_id")
  private long cancelledById;

  @OneToOne(mappedBy = "followup", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private AppointmentDetails details;

  //Change join column
//  @OneToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "payment_record_id")
//  private AppointmentPayment payment;

}
