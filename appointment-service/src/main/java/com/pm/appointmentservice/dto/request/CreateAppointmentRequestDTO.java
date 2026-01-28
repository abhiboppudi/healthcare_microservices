package com.pm.appointmentservice.dto.request;

import com.pm.appointmentservice.constants.AppointmentType;
import com.pm.appointmentservice.dto.response.AppointmentDetailsDTO;
import com.pm.appointmentservice.dto.response.AppointmentPaymentDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object for creating appointment")
public class CreateAppointmentRequestDTO {

  @NotNull
  @Schema(description = "The unique id of the patient")
  private Long patientId;

  @NotNull
  @Schema(description = "The unique id of the doctor")
  private Long doctorId;

  @NotNull
  @Schema(description = "The unique id of the hospital")
  private Long hospitalId;

  @NotNull
  @FutureOrPresent
  @Schema(description = "Date and Time of the appointment")
  private LocalDateTime appointmentTime;

  @Schema(description = "Type of the appointment")
  private AppointmentType type;

  // Optional first visit details
  @Schema(description = "Details of the appointment")
  private AppointmentDetailsDTO details;

  @Schema(description = "Payment details of appointment")
  private AppointmentPaymentDTO payment;
}
