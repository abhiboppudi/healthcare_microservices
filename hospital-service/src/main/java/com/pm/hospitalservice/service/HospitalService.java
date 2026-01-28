package com.pm.hospitalservice.service;

import com.pm.common.exceptions.DuplicateEntityException;
import com.pm.common.exceptions.NoEntityFoundException;
import com.pm.hospitalservice.dto.request.HospitalRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalResponseDTO;
import com.pm.hospitalservice.entity.Hospital;
import com.pm.hospitalservice.mapper.HospitalMapper;
import com.pm.hospitalservice.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pm.hospitalservice.constants.ErrorCodes.DUPLICATE_HOSPITAL;
import static com.pm.hospitalservice.constants.ErrorCodes.HOSPITAL_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class HospitalService {

  @Value("${spring.application.name}")
  private String serviceName;

  private final HospitalRepository hospitalRepository;

  private final HospitalMapper hospitalMapper;

  public HospitalResponseDTO findHospital(long hospitalId) {
    return hospitalRepository.findById(hospitalId)
        .map(hospitalMapper::toResponseDTO)
        .orElseThrow(() -> new NoEntityFoundException(serviceName, HOSPITAL_NOT_FOUND));
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public HospitalResponseDTO addNewHospital(HospitalRequestDTO requestDTO) {

    if (hospitalRepository.existsByName(requestDTO.getName())) {
      throw new DuplicateEntityException(serviceName, DUPLICATE_HOSPITAL);
    }

    Hospital hospital = hospitalMapper.toEntity(requestDTO);

    hospital = hospitalRepository.save(hospital);

    return hospitalMapper.toResponseDTO(hospital);
  }

  public List<HospitalResponseDTO> findActiveHospitals() {
    return hospitalRepository.findAll()
        .stream()
        .filter(Hospital::isActive)
        .map(hospitalMapper::toResponseDTO)
        .toList();
  }

  public HospitalResponseDTO updateHospitalDetails(long hospitalId, HospitalRequestDTO requestDTO) {

    if (hospitalRepository.existsByHospitalIdAndEmail(hospitalId, requestDTO.getEmail())) {
      throw new RuntimeException("Email entered is taken.");
    }

    Hospital hospital = hospitalRepository.save(hospitalMapper.toEntity(requestDTO));
    return hospitalMapper.toResponseDTO(hospital);
  }

  public void updateHospitalAsInactive(long hospitalId) {
    hospitalRepository.toggleStatus(hospitalId);
  }

}
