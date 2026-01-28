package com.pm.hospitalservice.entity;

import com.pm.common.constant.InvitationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class DoctorInvitation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long invitationId;

  private long hospitalId;

  private long doctorId;

  @Enumerated(EnumType.STRING)
  private InvitationStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime respondedAt;

  @Column(name = "expirydate")
  private LocalDateTime expiryDate;

}
