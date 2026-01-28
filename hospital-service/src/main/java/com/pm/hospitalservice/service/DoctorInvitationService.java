package com.pm.hospitalservice.service;

import com.pm.common.constant.BaseErrorCode;
import com.pm.common.constant.InvitationStatus;
import com.pm.common.dto.DoctorInvitationDTO;
import com.pm.common.exceptions.DuplicateEntityException;
import com.pm.common.exceptions.NoEntityFoundException;
import com.pm.hospitalservice.entity.DoctorInvitation;
import com.pm.hospitalservice.event.publisher.DoctorInvitationPublisher;
import com.pm.hospitalservice.mapper.DoctorInvitationMapper;
import com.pm.hospitalservice.repository.DoctorInvitationRepository;
import com.pm.hospitalservice.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.pm.hospitalservice.constants.ErrorCodes.DUPLICATE_INVITE;
import static com.pm.hospitalservice.constants.ErrorCodes.HOSPITAL_NOT_FOUND;
import static com.pm.hospitalservice.constants.ErrorCodes.INVITE_ACCEPTED;

@RequiredArgsConstructor
@Service
public class DoctorInvitationService {

  static final int EXPIRY_DAYS = 30;
  private final DoctorInvitationRepository invitationRepository;
  private final HospitalRepository hospitalRepository;
  private final DoctorInvitationMapper invitationMapper;
  private final DoctorInvitationPublisher publisher;
  @Value("${spring.application.name}")
  private String serviceName;

  @Transactional(propagation = Propagation.REQUIRED)
  public void inviteDoctor(long hospitalId, long doctorId) {

    if (!hospitalRepository.existsById(hospitalId)) {
      throw new NoEntityFoundException(serviceName, HOSPITAL_NOT_FOUND);
    }

    validateInvitationStatus(doctorId, hospitalId, serviceName);

    DoctorInvitation invitation = new DoctorInvitation();
    invitation.setHospitalId(hospitalId);
    invitation.setDoctorId(doctorId);
    invitation.setStatus(InvitationStatus.PENDING);
    invitation.setCreatedAt(LocalDateTime.now());
    invitation.setExpiryDate(LocalDateTime.now().plusDays(EXPIRY_DAYS));

    invitation = invitationRepository.save(invitation);

    //publish Invitation event for doctor service to consume
    publisher.publishInvitationEvent(invitation);
  }

  public List<DoctorInvitationDTO> listAllPendingInvitesByHospital(long hospitalId) {
    return invitationRepository.findByHospitalId(hospitalId)
        .stream()
        .filter(invitation -> invitation.getStatus() == InvitationStatus.PENDING)
        .map(invitationMapper::toResponseDTO)
        .toList();
  }

  public List<DoctorInvitationDTO> fetchDoctorInvitation(long doctor) {
    return invitationRepository.findByDoctorId(doctor)
        .stream()
        .map(invitationMapper::toResponseDTO)
        .toList();
  }

  public List<DoctorInvitationDTO> fetchAllHospitalInvitations(long hospitalId) {
    return invitationRepository.findByHospitalId(hospitalId)
        .stream()
        .map(invitationMapper::toResponseDTO)
        .toList();
  }

  public DoctorInvitationDTO updateStatus(long invitationId, long hospitalId, long doctorId, InvitationStatus status) {

    DoctorInvitation entity = invitationRepository.findById(invitationId)
        .map(invitation -> {
          invitation.setStatus(status);
          invitation = invitationRepository.save(invitation);
          return invitation;
        })
        .orElseThrow(() -> new RuntimeException("Value not found"));

    return invitationMapper.toResponseDTO(entity);

  }

  private void validateInvitationStatus(long doctorId, long hospitalId, String serviceName) {
    Map<InvitationStatus, BaseErrorCode> statusErrorMap = Map.of(
        InvitationStatus.PENDING, DUPLICATE_INVITE,
        InvitationStatus.ACCEPTED, INVITE_ACCEPTED
    );

    statusErrorMap.forEach((status, errorCode) ->
        invitationRepository.findByDoctorIdAndHospitalIdAndStatus(doctorId, hospitalId, status)
            .ifPresent(invitation -> {
              throw new DuplicateEntityException(serviceName, errorCode);
            })
    );
  }

}
