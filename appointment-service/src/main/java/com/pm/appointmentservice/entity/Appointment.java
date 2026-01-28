package com.pm.appointmentservice.entity;

import com.pm.appointmentservice.constants.AppointmentStatus;
import com.pm.appointmentservice.constants.AppointmentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appointment_id")
  private Long appointmentId;

  private Long patientId;

  private Long doctorId;

  private Long hospitalId;

  private LocalDateTime appointmentTime;

  @Enumerated(EnumType.STRING)
  private AppointmentStatus status;

  @Enumerated(EnumType.STRING)
  private AppointmentType type;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "appointment_id")
  private List<AppointmentFollowup> followups = new ArrayList<>();


}
