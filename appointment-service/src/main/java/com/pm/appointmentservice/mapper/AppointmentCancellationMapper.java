package com.pm.appointmentservice.mapper;

import com.pm.appointmentservice.dto.request.AppointmentCancellationRequestDTO;
import com.pm.appointmentservice.dto.response.AppointmentCancellationResponseDTO;
import com.pm.appointmentservice.entity.AppointmentCancellation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentCancellationMapper {

  AppointmentCancellation toEntity(AppointmentCancellationRequestDTO dto);

  AppointmentCancellationResponseDTO toDTO(AppointmentCancellation entity);

}
