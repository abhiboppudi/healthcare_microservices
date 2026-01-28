package com.pm.hospitalservice.event.listener;

import com.pm.doctor.invitation.response.DoctorInvitaionResponseEvent;
import com.pm.doctor.invitation.response.DoctorInvitationResponsePayload;
import com.pm.hospitalservice.repository.DoctorInvitationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Slf4j
public class DoctorInvitationListener {

  private final DoctorInvitationRepository invitationRepository;

  @KafkaListener(
      topics = "${spring.kafka.topic.hospital-doctor-invitation}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void handleInvitationResponse(DoctorInvitaionResponseEvent event) {

    DoctorInvitationResponsePayload payload = event.getPayload();

    log.info("Doctor responded to invite with status : {}", payload.getInvitation().getStatus());

    invitationRepository.findById( event.getPayload().getInvitationId() )
        .ifPresent(invitation -> {
          invitation.setStatus( payload.getInvitation().getStatus() );
          invitation.setRespondedAt(LocalDateTime.now());
          invitationRepository.save(invitation);
        });

  }


}
