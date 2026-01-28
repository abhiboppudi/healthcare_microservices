package com.pm.appointmentservice.dto.request;

import com.pm.appointmentservice.constants.AppointmentStatus;
import com.pm.appointmentservice.dto.response.AppointmentFollowupDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAppointmentRequestDTO {

  @NotNull
  @Schema(description = "The unique id of the patient")
  private Long appointmentId;

  // Appointment-level updates
  private LocalDateTime appointmentTime;

  private AppointmentStatus status;

  // Add or update followups
  private List<AppointmentFollowupDTO> followups;

}
