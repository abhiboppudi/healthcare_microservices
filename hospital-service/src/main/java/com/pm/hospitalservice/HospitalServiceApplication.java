package com.pm.hospitalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pm.common", "com.pm.hospitalservice"})
public class HospitalServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(HospitalServiceApplication.class, args);
  }

}
