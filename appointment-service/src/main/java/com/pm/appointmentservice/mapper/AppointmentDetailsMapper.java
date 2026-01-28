package com.pm.appointmentservice.mapper;

import com.pm.appointmentservice.dto.response.AppointmentDetailsDTO;
import com.pm.appointmentservice.entity.AppointmentDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentDetailsMapper {

  AppointmentDetailsDTO toDto(AppointmentDetails entity);

  AppointmentDetails toEntity(AppointmentDetailsDTO dto);
}
