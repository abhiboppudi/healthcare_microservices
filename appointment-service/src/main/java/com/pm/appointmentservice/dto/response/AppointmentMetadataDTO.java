package com.pm.appointmentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentMetadataDTO {

  private Long id;

  private String metaKey;

  private String metaValue;

  private LocalDateTime createdAt;

}
