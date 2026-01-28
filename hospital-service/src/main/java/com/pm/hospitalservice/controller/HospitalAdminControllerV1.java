package com.pm.hospitalservice.controller;

import com.pm.hospitalservice.dto.request.HospitalAdministratorRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalAdministratorResponseDTO;
import com.pm.hospitalservice.service.HospitalAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "hospital-admin-user-API-v1", description = "API for managing administrators linked to a hospital. " +
    "Allows adding, removing, and listing admins for operational control.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospital-admin")
public class HospitalAdminControllerV1 {

  private final HospitalAdminService adminService;

  @PostMapping("/hospital/{hospitalId}")
  @Operation(description = "Create an administrator for a hospital")
  public ResponseEntity<HospitalAdministratorResponseDTO> createAdmin(@PathVariable("hospitalId") long hospitalId,
                                                                      @RequestBody
                                                                      HospitalAdministratorRequestDTO requestDTO) {
    HospitalAdministratorResponseDTO responseDTO = adminService.addAdministrator(hospitalId, requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @DeleteMapping("/{adminId}")
  @Operation(description = "mark administrator as inactive, once inactive admin cannot access hospital service")
  public ResponseEntity<Void> toggleStatus(@PathVariable("adminId") long adminId) {
    adminService.toggleStatus(adminId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/{adminId}")
  @Operation(description = "Gets Hospital administrator profile by id")
  public ResponseEntity<HospitalAdministratorResponseDTO> findById(@PathVariable("adminId") long adminId) {
    HospitalAdministratorResponseDTO responseDTO = adminService.findAdminById(adminId);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/active")
  @Operation(description = "Gets Hospital administrator profile by id")
  public ResponseEntity<List<HospitalAdministratorResponseDTO>> findAllActiveAdmins() {
    List<HospitalAdministratorResponseDTO> responseDTOs = adminService.findActiveAdmins();
    return ResponseEntity.ok(responseDTOs);
  }

}
