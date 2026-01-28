package com.pm.hospitalservice.mapper;

import com.pm.hospitalservice.dto.request.HospitalAdministratorRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalAdministratorResponseDTO;
import com.pm.hospitalservice.entity.HospitalAdministrator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

  HospitalAdministratorResponseDTO toResponseDTO(HospitalAdministrator administrator);

  HospitalAdministrator toEntity(HospitalAdministratorRequestDTO requestDTO);

}
