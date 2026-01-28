package com.pm.common.dto;

import com.pm.common.constant.InvitationStatus;
import com.pm.common.validation.AllowedEnumValues;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InvitationResponseDTO {

  @NotNull
  long invitationId;

  @NotNull
  long doctorId;

  @NotNull
  long hospitalId;

  @NotNull
  @AllowedEnumValues(enumClass = InvitationStatus.class, values = {"ACCEPTED",
      "REJECTED"}, message = "{allowed.enum.values.invalid}")
  InvitationStatus status;

}
