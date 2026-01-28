package com.pm.appointmentservice.dto.response;

import com.pm.appointmentservice.constants.FollowupStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentFollowupDTO {

  private Long followupId;

  private Boolean followupRequired;

  private LocalDateTime followupDate;

  private String followupReason;

  private String followupNotes;

  private Long recommendedBy;

  private FollowupStatus status; // ACTIVE, COMPLETED, CANCELLED

  private LocalDateTime cancelledAt;

  private String cancellationReason;

  private Long cancelledById;

  private AppointmentDetailsDTO details;

  private AppointmentPaymentDTO payment;

}
