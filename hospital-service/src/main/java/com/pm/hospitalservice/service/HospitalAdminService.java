package com.pm.hospitalservice.service;

import com.pm.common.exceptions.DuplicateEntityException;
import com.pm.common.exceptions.NoEntityFoundException;
import com.pm.hospitalservice.dto.request.HospitalAdministratorRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalAdministratorResponseDTO;
import com.pm.hospitalservice.entity.HospitalAdministrator;
import com.pm.hospitalservice.mapper.AdminMapper;
import com.pm.hospitalservice.repository.HospitalAdminRepository;
import com.pm.hospitalservice.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pm.hospitalservice.constants.ErrorCodes.ADMIN_NOT_FOUND;
import static com.pm.hospitalservice.constants.ErrorCodes.DUPLICATE_ADMINISTRATOR;

@RequiredArgsConstructor
@Service
public class HospitalAdminService {

  @Value("${spring.application.name}")
  private String serviceName;

  private final HospitalAdminRepository adminRepository;

  private final HospitalRepository hospitalRepository;

  private final AdminMapper adminMapper;

  public HospitalAdministratorResponseDTO addAdministrator(final long hospitalId,
                                                           final HospitalAdministratorRequestDTO requestDTO) {

    if (!hospitalRepository.existsById(hospitalId)) {
      throw new NoEntityFoundException(serviceName, DUPLICATE_ADMINISTRATOR, hospitalId);
    }

    if (adminRepository.existsByFirstNameAndLastName(requestDTO.getFirstName(), requestDTO.getLastName())) {
      throw new DuplicateEntityException(serviceName, DUPLICATE_ADMINISTRATOR, requestDTO.getFirstName(),
          requestDTO.getLastName());
    }

    HospitalAdministrator administrator = adminMapper.toEntity(requestDTO);
    administrator = adminRepository.save(administrator);

    return adminMapper.toResponseDTO(administrator);
  }

  public void toggleStatus(final long adminId) {
    adminRepository.toggleActiveStatus(adminId);
  }

  public HospitalAdministratorResponseDTO findAdminById(final long adminId) {
    return adminRepository.findById(adminId)
        .map(adminMapper::toResponseDTO)
        .orElseThrow(() -> new NoEntityFoundException(serviceName, ADMIN_NOT_FOUND));
  }

  public List<HospitalAdministratorResponseDTO> findActiveAdmins() {
    return adminRepository.findAll()
        .stream()
        .filter(HospitalAdministrator::isActive)
        .map(adminMapper::toResponseDTO)
        .toList();
  }

}
