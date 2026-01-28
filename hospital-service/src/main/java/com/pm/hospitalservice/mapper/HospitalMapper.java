package com.pm.hospitalservice.mapper;

import com.pm.hospitalservice.dto.request.HospitalRequestDTO;
import com.pm.hospitalservice.dto.response.HospitalResponseDTO;
import com.pm.hospitalservice.entity.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HospitalMapper {

  HospitalResponseDTO toResponseDTO(Hospital hospital);

  Hospital toEntity(HospitalRequestDTO requestDTO);
}
