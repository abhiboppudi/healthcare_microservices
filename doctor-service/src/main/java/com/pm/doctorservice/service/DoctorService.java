package com.pm.doctorservice.service;

import com.pm.common.dto.DoctorInvitationDTO;
import com.pm.common.exceptions.DuplicateEntityException;
import com.pm.common.exceptions.NoEntityFoundException;
import com.pm.doctorservice.dto.DoctorDTO;
import com.pm.common.dto.InvitationResponseDTO;
import com.pm.doctorservice.entity.Doctor;
import com.pm.doctorservice.event.publisher.DoctorEventPublisher;
import com.pm.doctorservice.mapper.DoctorMapper;
import com.pm.doctorservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.pm.doctorservice.constant.errors.ErrorCodes.DOC_DUPLICATE;
import static com.pm.doctorservice.constant.errors.ErrorCodes.DOC_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DoctorService {

  private final DoctorRepository doctorRepository;

  private final DoctorMapper doctorMapper;

  private final RestTemplate restTemplate;

  private final DoctorEventPublisher doctorEventPublisher;

  @Value("${spring.application.name}")
  private String serviceName;

  public List<DoctorDTO> getAllActiveDoctors(){
      List<Doctor> doctors = doctorRepository.findByActiveStatusTrue();
      return (doctors.stream()
                      .map(doctorMapper::toDoctorDTO)
                      .toList());
  }

  public DoctorDTO getDoctor(Long doctorID) {
    return (DoctorDTO) doctorRepository.findById(doctorID)
        .map(doctorMapper::toDoctorDTO)
        .orElseThrow(() -> new NoEntityFoundException(serviceName, DOC_NOT_FOUND, doctorID));
  }

  public DoctorDTO createDoctor(DoctorDTO dto) {

    if (isDocExists(dto)) {
      throw new DuplicateEntityException(serviceName, DOC_DUPLICATE);
    }

    Doctor doctor = doctorMapper.toDoctor(dto);
    doctor = doctorRepository.save(doctor);

    return doctorMapper.toDoctorDTO(doctor);
  }

  public DoctorDTO updateDoctorProfile(Long id, DoctorDTO dto) {

    if (!isDocExists(dto)) {
      throw new NoEntityFoundException(serviceName, DOC_NOT_FOUND, id);
    }

    //id & email should not change while updating

    Doctor doctor = doctorMapper.toDoctor(dto);
    doctor = doctorRepository.save(doctor);

    return doctorMapper.toDoctorDTO(doctor);
  }

  public List<DoctorDTO> getDoctorsByHospital(Long hospitalId) {
    //No need for Optional.ofNullable(...) here, because Spring Data guarantees a non-null list.
    return doctorRepository.findDoctorsByHospitalId(hospitalId)
        .stream()
        .map(doctorMapper::toDoctorDTO)
        .toList();
  }

  public List<DoctorInvitationDTO> fetchHospitalInvites(long doctorId) {

    ResponseEntity<List<DoctorInvitationDTO>> responseEntity =
        restTemplate.exchange("http://hospital-service/api/v1/invite/doctor/" + doctorId, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<DoctorInvitationDTO>>() {
            });

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      return responseEntity.getBody();
    }

    throw new NoEntityFoundException(serviceName, null);
  }

  //Move to event
  public void updateInvite(InvitationResponseDTO responseDTO) {

    doctorEventPublisher.publisInvitationResponse(responseDTO.getInvitationId(), responseDTO.getHospitalId(),
        responseDTO.getDoctorId(), responseDTO.getStatus());

  }

//  This method should be registered as Kafka event listener, once hospital
//  assigns doctor, this service will be notified and then should update HospitalAssignment table
//  public void assignDoctorToHospital(Long id, Long hospitalId) {
//
//  }

  private boolean isDocExists(DoctorDTO dto) {
    return doctorRepository.existsByEmailId(dto.getEmailId());
  }


}
