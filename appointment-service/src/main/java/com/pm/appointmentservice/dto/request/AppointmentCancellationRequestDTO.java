package com.pm.appointmentservice.dto.request;

import com.pm.appointmentservice.constants.CancelledByType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCancellationRequestDTO {

  @NotNull
  @Schema(description = "The unique id of the patient/doctor")
  private Long cancelledBy;

  @NotNull
  @Schema(description = "The represents actor who cancelled appointment, It can be Doctor or Patient or Admin")
  private CancelledByType type;

  @Schema(description = "Reason for appointment cancellation")
  private String reason;


}
