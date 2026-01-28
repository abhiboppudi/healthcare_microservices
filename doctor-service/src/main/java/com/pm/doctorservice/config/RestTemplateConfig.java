package com.pm.doctorservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  @LoadBalanced
  //The @LoadBalanced annotation tells Spring Cloud to resolve service names (like http://producer-service) via Eureka.
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
