package com.pm.appointmentservice.mapper;

import com.pm.appointmentservice.dto.response.AppointmentFollowupDTO;
import com.pm.appointmentservice.entity.AppointmentFollowup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentFollowupMapper {

  AppointmentFollowupDTO toDTO(AppointmentFollowup entity);
  
  AppointmentFollowup toEntity(AppointmentFollowupDTO dto);

}
