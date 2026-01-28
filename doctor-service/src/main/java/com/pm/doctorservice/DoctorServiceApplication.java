package com.pm.doctorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pm.common", "com.pm.doctorservice"})
public class DoctorServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DoctorServiceApplication.class, args);
  }

}
