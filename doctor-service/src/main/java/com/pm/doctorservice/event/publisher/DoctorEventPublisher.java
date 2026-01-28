package com.pm.doctorservice.event.publisher;

import com.pm.common.constant.InvitationStatus;
import com.pm.doctor.invitation.Invitation;
import com.pm.doctor.invitation.response.DoctorInvitaionResponseEvent;
import com.pm.doctor.invitation.response.DoctorInvitationResponseKey;
import com.pm.doctor.invitation.response.DoctorInvitationResponsePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class DoctorEventPublisher {

  @Value("${spring.kafka.topic.hospital-doctor-invitation:hospital.doctor.invitation}")
  private String topicName;

  private final KafkaTemplate<DoctorInvitationResponseKey, DoctorInvitaionResponseEvent> kafkaTemplate;

  public void publisInvitationResponse(long invitationId, long hospitalId, long doctorId, InvitationStatus status){

    DoctorInvitationResponseKey key = DoctorInvitationResponseKey.newBuilder()
        .setInvitationId(invitationId+"")
        .build();

    DoctorInvitationResponsePayload payload = DoctorInvitationResponsePayload.newBuilder()
        .setInvitationId(invitationId)
        .setHospitalId(hospitalId)
        .setDoctorId(doctorId)
        .setInvitation(Invitation.newBuilder().setStatus(status).build() )
        .build();

    DoctorInvitaionResponseEvent event = DoctorInvitaionResponseEvent.newBuilder()
        .setEventId(String.valueOf(UUID.randomUUID()))
        .setEventType("INVITATION_RESPONSE")
        .setEventTimestamp(Instant.now().toString())
        .setPayload(payload)
        .build();

    kafkaTemplate.send(topicName, key, event).whenComplete((result, ex) -> {
        if(ex == null){
          log.info("Published Doctor invitation response to topic {} partition={} offset={}", topicName, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        }else {
          log.error("Error publishing Doctor invitation response to topic {} partition={} offset={}", topicName, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        }
    });

  }

}
