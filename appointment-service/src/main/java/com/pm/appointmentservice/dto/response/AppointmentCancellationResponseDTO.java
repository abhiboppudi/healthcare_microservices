package com.pm.appointmentservice.dto.response;

import com.pm.appointmentservice.constants.AppointmentStatus;
import com.pm.appointmentservice.constants.CancelledByType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCancellationResponseDTO {
  private Long appointmentId;

  private AppointmentStatus status;

  private LocalDateTime cancelledAt;

  private Long cancelledBy;

  private CancelledByType cancelledByType;

  private String cancellationReason;
}
