package com.pm.appointmentservice.mapper;

import com.pm.appointmentservice.dto.request.CreateAppointmentRequestDTO;
import com.pm.appointmentservice.dto.response.AppointmentViewDTO;
import com.pm.appointmentservice.entity.Appointment;
import com.pm.appointmentservice.entity.AppointmentDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AppointmentDetailsMapper.class})
public interface AppointmentMapper {

  AppointmentViewDTO toAppointmentView(Appointment appointment);

  Appointment toAppointment(CreateAppointmentRequestDTO dto);

  AppointmentDetails toAppointmentDetails(CreateAppointmentRequestDTO dto);
}
