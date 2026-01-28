package com.pm.doctorservice.mapper;

import com.pm.doctorservice.dto.DoctorFeedbackDTO;
import com.pm.doctorservice.entity.DoctorFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorFeedbackMapper {

  DoctorFeedback toDoctorFeedback(DoctorFeedbackDTO dto);

  @Mapping(target = "averageRating", //expression = "java(feedback.getAverageRating())")
      expression = "java(feedback.getFeedbackCount() == null || feedback.getFeedbackCount() == 0 ? " +
          "0.0 : feedback.getTotalStars() / feedback.getFeedbackCount())")
  DoctorFeedbackDTO toDoctorFeedbackDTO(DoctorFeedback feedback);

}
