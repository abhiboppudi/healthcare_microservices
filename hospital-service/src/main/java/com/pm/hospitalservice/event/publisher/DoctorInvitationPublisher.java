package com.pm.hospitalservice.event.publisher;

import com.pm.doctor.invitation.DoctorInvitationEvent;
import com.pm.doctor.invitation.DoctorInvitationEventType;
import com.pm.doctor.invitation.DoctorInvitationKey;
import com.pm.doctor.invitation.DoctorInvitationPayload;
import com.pm.doctor.invitation.Invitation;
import com.pm.hospitalservice.entity.DoctorInvitation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;

@Slf4j
@Component
@RequiredArgsConstructor
public class DoctorInvitationPublisher {

  private final KafkaTemplate<DoctorInvitationKey, DoctorInvitationEvent> kafkaTemplate;

  @Value("${spring.kafka.topic.hospital-doctor-invitation:hospital.doctor.invitation}")
  private String topicName;

  public void publishInvitationEvent(DoctorInvitation invitation) {
    // Build payload
    DoctorInvitationPayload payload = getDoctorInvitationPayload(invitation);

    // Wrap in event envelope
    DoctorInvitationEvent event = getDoctorInvitationEvent(invitation, payload);

    // Build key
    DoctorInvitationKey key = DoctorInvitationKey.newBuilder()
        .setInvitationId(String.valueOf(invitation.getInvitationId()))
        .build();

    // Send with callback for resilience
    kafkaTemplate.send(topicName, key, event).whenComplete((result, ex) -> {

      if (ex == null) {
        log.info("Published DoctorInvitationEvent to topic={} partition={} offset={}", topicName,
            result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
      } else {
        log.error("Failed to publish DoctorInvitationEvent for hospitalId={} doctorId={} invitationId={}",
            invitation.getHospitalId(),
            invitation.getDoctorId(), invitation.getInvitationId(), ex);
      }

    });

  }

  private DoctorInvitationEvent getDoctorInvitationEvent(DoctorInvitation invitation,
                                                         DoctorInvitationPayload payload) {
    return DoctorInvitationEvent.newBuilder()
        .setPayload(payload)
        .setEventId(String.valueOf(invitation.getInvitationId()))
        .setOccurredAt(Instant.now())
        .setEventType(DoctorInvitationEventType.CREATED)
        .setProducer("hospital-service")
        .setSchemaVersion("1")
        .build();
  }

  private DoctorInvitationPayload getDoctorInvitationPayload(DoctorInvitation invitation) {
    return DoctorInvitationPayload.newBuilder()
        .setDoctorId(invitation.getDoctorId())
        .setHospitalId(invitation.getHospitalId())
        .setInvitationId(String.valueOf(invitation.getInvitationId()))
        .setInvitation(Invitation.newBuilder()
            .setStatus(invitation.getStatus()).build())
        //.setStatus(invitation.getStatus())
        .setInvitedBy("Administrator")
        .setExpiresAt(invitation.getExpiryDate().toInstant(ZoneOffset.UTC)) // safe conversion
        .build();
  }

}
