package com.pm.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorInvitationDTO {

  private long invitationId;

  private long hospitalId;

  private long doctorId;

  private String status;

  private LocalDateTime createdAt;

}
