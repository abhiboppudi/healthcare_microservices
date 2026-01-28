package com.pm.appointmentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetailsDTO {

  private String reason;

  private String patientNotes;

  private String doctorNotes;

  private String source;

}
