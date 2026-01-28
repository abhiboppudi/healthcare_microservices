package com.pm.doctorservice.mapper;

import com.pm.doctorservice.dto.DoctorDTO;
import com.pm.doctorservice.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

  DoctorDTO toDoctorDTO(Doctor doctor);

  Doctor toDoctor(DoctorDTO dto);
}
