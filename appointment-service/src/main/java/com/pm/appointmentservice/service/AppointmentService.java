package com.pm.appointmentservice.service;

import com.pm.appointmentservice.constants.AppointmentStatus;
import com.pm.appointmentservice.dto.request.AppointmentCancellationRequestDTO;
import com.pm.appointmentservice.dto.request.CreateAppointmentRequestDTO;
import com.pm.appointmentservice.dto.response.AppointmentCancellationResponseDTO;
import com.pm.appointmentservice.dto.response.AppointmentFollowupDTO;
import com.pm.appointmentservice.dto.response.AppointmentViewDTO;
import com.pm.appointmentservice.entity.Appointment;
import com.pm.appointmentservice.entity.AppointmentCancellation;
import com.pm.appointmentservice.entity.AppointmentDetails;
import com.pm.appointmentservice.entity.AppointmentFollowup;
import com.pm.appointmentservice.exception.DuplicateAppointmentException;
import com.pm.appointmentservice.exception.ResourceNotFoundException;
import com.pm.appointmentservice.mapper.AppointmentCancellationMapper;
import com.pm.appointmentservice.mapper.AppointmentDetailsMapper;
import com.pm.appointmentservice.mapper.AppointmentFollowupMapper;
import com.pm.appointmentservice.mapper.AppointmentMapper;
import com.pm.appointmentservice.repository.AppointmentCancellationRepository;
import com.pm.appointmentservice.repository.AppointmentFollowupRepository;
import com.pm.appointmentservice.repository.AppointmentRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;

  private final AppointmentFollowupRepository appointmentFollowupRepository;

  private final AppointmentCancellationRepository appointmentCancellationRepository;

  private final AppointmentMapper appointmentMapper;

  private final AppointmentFollowupMapper followupMapper;

  private final AppointmentDetailsMapper detailsMapper;

  private final AppointmentCancellationMapper appointmentCancellationMapper;

  private static final List<AppointmentStatus> activeAppointmentStatuses =
      Arrays.asList(AppointmentStatus.PENDING, AppointmentStatus.CONFIRMED);

  @Transactional(readOnly = true)
  public AppointmentViewDTO getAppointment(@NotNull Long appointmentId) {

    Appointment appointment = appointmentRepository.findById(appointmentId)
        .orElseThrow(() -> new ResourceNotFoundException("No appointment found with given id"));

    AppointmentViewDTO viewDTO = appointmentMapper.toAppointmentView(appointment);

    List<AppointmentFollowup> appointmentFollowups = appointment.getFollowups();

    Optional.ofNullable(appointmentFollowups)
        .orElse(List.of())
        .stream()
        .map(this::mapFollowupToDto)
        .toList();

    return viewDTO;
  }

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
  public AppointmentViewDTO createAppointment(CreateAppointmentRequestDTO dto) {

    //Validate for avoiding double appointment
    if (hasActiveAppointments(dto.getPatientId())) {
      throw new DuplicateAppointmentException(
          "An appointment already exists for patient with id " + dto.getPatientId());
    }

    //If no appointment exists, createAppointment
    Appointment appointment = appointmentMapper.toAppointment(dto);
    appointment.setStatus(AppointmentStatus.PENDING);
    appointment = appointmentRepository.save(appointment);

    AppointmentFollowup defaultFollowup = new AppointmentFollowup();
    defaultFollowup.setAppointmentId(appointment.getAppointmentId());
    AppointmentDetails details = appointmentMapper.toAppointmentDetails(dto);
    defaultFollowup.setDetails(details);
    details.setFollowup(defaultFollowup);

    defaultFollowup = appointmentFollowupRepository.save(defaultFollowup);

    AppointmentViewDTO viewDTO = appointmentMapper.toAppointmentView(appointment);
    //viewDTO.setFollowups(Arrays.asList(null));

    return appointmentMapper.toAppointmentView(appointment);

  }

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
  public AppointmentCancellationResponseDTO cancelAppointment(long appointmentId,
                                                              AppointmentCancellationRequestDTO dto) {

    //Check if an appointment exists, either PENDING or BOOKED, then update
    return appointmentRepository.findByAppointmentIdAndStatusIn(appointmentId, activeAppointmentStatuses)
        .map(appointment -> {
          AppointmentCancellation cancellation = appointmentCancellationMapper.toEntity(dto);
          cancellation.setAppointmentId(appointmentId);
          cancellation.setCancelledAt(LocalDateTime.now());
          cancellation = appointmentCancellationRepository.save(cancellation);
          return appointmentCancellationMapper.toDTO(cancellation);
        }).orElseThrow(() -> new ResourceNotFoundException(
            "No Appointment found with " + appointmentId + ". Cancellation cannot be done."));

  }

  private boolean hasActiveAppointments(Long patientId) {
    List<Appointment> appointments =
        appointmentRepository.findByPatientIdAndStatusIn(patientId, activeAppointmentStatuses);

    return !appointments.isEmpty();
  }

  private AppointmentFollowupDTO mapFollowupToDto(AppointmentFollowup followup) {
    AppointmentFollowupDTO dto = followupMapper.toDTO(followup);
    Optional.ofNullable(followup.getDetails())
        .map(detailsMapper::toDto)
        .ifPresent(dto::setDetails);
    return dto;
  }

}
