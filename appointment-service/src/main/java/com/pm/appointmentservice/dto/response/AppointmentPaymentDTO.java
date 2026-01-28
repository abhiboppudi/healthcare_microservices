package com.pm.appointmentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentPaymentDTO {

  private Long paymentRecordId;

  private Boolean paymentRequired;

  private Boolean paymentCompleted;
  
  private Long paymentId; // Reference to Billing Service

  private Double consultationFee;

}
