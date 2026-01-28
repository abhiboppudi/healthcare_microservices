package com.pm.appointmentservice.entity;

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

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "appointment_details")
public class AppointmentDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long detailsId;

  // private Long followupId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "followup_id", nullable = false)
  private AppointmentFollowup followup;

  @Column(columnDefinition = "TEXT")
  private String reason;

  @Column(columnDefinition = "TEXT")
  private String patientNotes;

  @Column(columnDefinition = "TEXT")
  private String doctorNotes;

  private String source; // MOBILE_APP, WEB, ADMIN_PORTAL
}
