package com.pm.hospitalservice.mapper;

import com.pm.common.constant.SessionTiming;
import com.pm.common.constant.SlotType;
import com.pm.hospitalservice.dto.request.HospitalTimingsRequestDTO;
import com.pm.hospitalservice.dto.response.AppointmentSlotResponseDTO;
import com.pm.hospitalservice.dto.response.HospitalTimingsResponseDTO;
import com.pm.hospitalservice.entity.AppointmentSlot;
import com.pm.hospitalservice.entity.HospitalTimings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TimingsMapper {


  @Named("toSessionTiming")
  default int mapToStatus(SessionTiming sessionTiming) {
    return sessionTiming == null ? -1 : sessionTiming.getCode();
  }

  @Named("slotTypeToInt")
  default int slotTypeToInt(SlotType slotType) {
    return slotType == null ? -1 : slotType.getCode();
  }

  @Mapping(source = "slotType", target = "slotType", qualifiedByName = "slotTypeToInt")
  AppointmentSlotResponseDTO toAppointmentResponse(AppointmentSlot slot);

  HospitalTimings toEntity(HospitalTimingsRequestDTO requestDTO);

  @Mapping(source = "session", target = "session", qualifiedByName = "toSessionTiming")
  HospitalTimingsResponseDTO toResponseDTO(HospitalTimings hospitalTimings);


}
