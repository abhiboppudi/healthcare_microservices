package com.pm.hospitalservice.controller;

import com.pm.common.dto.DoctorInvitationDTO;
import com.pm.common.dto.InvitationResponseDTO;
import com.pm.hospitalservice.service.DoctorInvitationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/invite")
@Tag(name = "api-doctor-invite-v1")
public class DoctorInvitationController {

  private final DoctorInvitationService invitationService;

  @PostMapping("/hospitals/{hospitalId}/doctor/{doctorId}")
  public ResponseEntity<Void> postInvitation(@PathVariable("hospitalId") long hospitalId,
                                             @PathVariable("doctorId") long doctorId) {
    invitationService.inviteDoctor(hospitalId, doctorId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/hospitals/{hospitalId}/doctor/{doctorId}")
  public ResponseEntity<Void> cancelInvite(@PathVariable("hospitalId") long hospitalId,
                                           @PathVariable("doctorId") long doctorId) {

    return null;
  }

  @PatchMapping("/doctor/update")
  public ResponseEntity<DoctorInvitationDTO> updateDoctorInvite(@Valid @RequestBody InvitationResponseDTO invitationResponse) {
    DoctorInvitationDTO dto = invitationService.updateStatus(invitationResponse.getInvitationId(), invitationResponse.getHospitalId(), invitationResponse.getDoctorId(), invitationResponse.getStatus());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
  }

  @GetMapping("/hospitals/{hospitalId}/pending")
  public ResponseEntity<List<DoctorInvitationDTO>> listAllPendingInvites(
      @PathVariable("hospitalId") long hospitalId) {
    List<DoctorInvitationDTO> pendingInvites = invitationService.listAllPendingInvitesByHospital(hospitalId);
    return ResponseEntity.ok(pendingInvites);
  }

  @GetMapping("/doctor/{doctorId}")
  public ResponseEntity<List<DoctorInvitationDTO>> fetchDoctorInvite(@PathVariable("doctorId") long doctorId) {
    List<DoctorInvitationDTO> responseDTO = invitationService.fetchDoctorInvitation(doctorId);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/hospital/{hospitalId}/")
  public ResponseEntity<List<?>> fetchInvite(@PathVariable("hospitalId") long hospitalId) {
    List<DoctorInvitationDTO> pendingInvites = invitationService.fetchAllHospitalInvitations(hospitalId);
    return ResponseEntity.ok(pendingInvites);
  }

}
