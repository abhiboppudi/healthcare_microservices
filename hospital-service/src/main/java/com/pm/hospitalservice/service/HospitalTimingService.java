package com.pm.hospitalservice.service;

import com.pm.hospitalservice.dto.request.HospitalTimingsRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalTimingsResponseDTO;
import com.pm.hospitalservice.entity.HospitalTimings;
import com.pm.hospitalservice.mapper.TimingsMapper;
import com.pm.hospitalservice.repository.HospitalRepository;
import com.pm.hospitalservice.repository.HospitalTimingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HospitalTimingService {

  private final HospitalRepository hospitalRepository;

  private final HospitalTimingsRepository timingsRepository;

  private final TimingsMapper timingsMapper;

  public HospitalTimingsResponseDTO addTimings(HospitalTimingsRequestDTO requestDTO) {

    hospitalRepository.isHospitalExistsAndActive(requestDTO.getHospitalId())
        .orElseThrow(() ->
            new RuntimeException("Invalid Hospital or Hospital is not active."));

    //Also check doctor id

    HospitalTimings timings = timingsMapper.toEntity(requestDTO);

    timings = timingsRepository.save(timings);

    return timingsMapper.toResponseDTO(timings);
  }

  public void deleteTimingsForDoctor(long hospitalId, long doctorId){

    hospitalRepository.isHospitalExistsAndActive(hospitalId)
        .orElseThrow(() ->
            new RuntimeException("Invalid Hospital or Hospital is not active."));

    //Also check doctor id

    //remove record from DoctorAssignments table

  }

  public List<HospitalTimingsResponseDTO> getTimings(long hospitalId) {

    hospitalRepository.isHospitalExistsAndActive(hospitalId)
        .orElseThrow(() ->
            new RuntimeException("Invalid Hospital or Hospital is not active."));

    //Also check doctor id

    return timingsRepository.findByHospital(hospitalId)
        .stream()
        .map(timingsMapper::toResponseDTO)
        .toList();
  }

}
