package com.pm.hospitalservice.mapper;

import com.pm.common.dto.DoctorInvitationDTO;
import com.pm.hospitalservice.entity.DoctorInvitation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorInvitationMapper {

  DoctorInvitationDTO toResponseDTO(DoctorInvitation invitation);

}
