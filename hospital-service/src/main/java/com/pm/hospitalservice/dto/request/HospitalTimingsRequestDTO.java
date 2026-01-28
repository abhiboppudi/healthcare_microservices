package com.pm.hospitalservice.dto.request;

import com.pm.common.constant.SessionTiming;
import lombok.Data;

import java.time.LocalTime;

@Data
public class HospitalTimingsRequestDTO {

  private long hospitalId;

  private SessionTiming session;              // enum ordinal

  private int slotIntervalMinutes;

  private LocalTime startTime;

  private LocalTime endTime;

}
