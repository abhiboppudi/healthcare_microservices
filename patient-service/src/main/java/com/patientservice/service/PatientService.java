package com.patientservice.service;

import com.patientservice.database.entities.PatientEntity;
import com.patientservice.exception.NoPatientFoundException;
import com.patientservice.repository.PatientRepository;
import com.patientservice.request.dto.PatientDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

  private final PatientRepository patientRepository;

  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public PatientDTO getPatient(long id) {
    Optional<PatientEntity> entity = patientRepository.findById(id);

    return entity.map(this::mapDTO)
        .orElseThrow(() -> new RuntimeException("No patient found with ID : " + id));
  }

  public List<PatientDTO> findAllPatients() {
    List<PatientEntity> entityList = patientRepository.findAll();
    return entityList.stream().map(this::mapDTO).collect(Collectors.toCollection(ArrayList::new));
  }

  public PatientDTO createPatient(PatientDTO patient) {
    PatientEntity entity = mapEntity(patient);
    entity = patientRepository.save(entity);
    return mapDTO(entity);
  }

  public PatientDTO updatePatient(final PatientDTO patient) {
    return patientRepository.findById(patient.getId()).map(patientEntity -> {
      PatientEntity entity = mapEntity(patient);
      entity = patientRepository.save(entity);
      return mapDTO(entity);
    }).orElseThrow(() -> new NoPatientFoundException("No patient found with id " + patient.getId()));
  }

  private PatientDTO mapDTO(PatientEntity entity) {
    PatientDTO patient = new PatientDTO();
    patient.setId(entity.getId());
    patient.setFirstName(entity.getFirstName());
    patient.setLastName(entity.getLastName());
    patient.setEmail(entity.getEmail());
    patient.setPhone(entity.getPhone());
    patient.setInsuranceNumber(entity.getInsuranceNumber());
    patient.setRegisteredDate(entity.getRegisteredDate());
    return patient;
  }

  private PatientEntity mapEntity(PatientDTO patient) {
    PatientEntity entity = new PatientEntity();
    if (patient.getId() != null) {
      entity.setId(patient.getId());
    }
    entity.setFirstName(patient.getFirstName());
    entity.setLastName(patient.getLastName());
    entity.setEmail(patient.getEmail());
    entity.setPhone(patient.getPhone());
    entity.setInsuranceNumber(patient.getInsuranceNumber());
    entity.setRegisteredDate(patient.getRegisteredDate());
    return entity;
  }

}
