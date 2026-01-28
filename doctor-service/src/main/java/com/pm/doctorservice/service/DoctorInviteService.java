package com.pm.doctorservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class DoctorInviteService {

  private final RestTemplate restTemplate;

  void fetchDoctorInvites(long doctor){
    restTemplate.getForEntity("",Object.class);
  }

}
