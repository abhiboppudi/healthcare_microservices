package com.pm.appointmentservice.dto.response;

import com.pm.appointmentservice.constants.AppointmentStatus;
import com.pm.appointmentservice.constants.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentViewDTO {

  private Long appointmentId;

  private Long patientId;

  private Long doctorId;

  private Long hospitalId;

  private LocalDateTime appointmentTime;

  private AppointmentStatus status;

  private AppointmentType type;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private List<AppointmentFollowupDTO> followups;
  
  private List<AppointmentMetadataDTO> metadata;
}
