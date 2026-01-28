package com.pm.doctorservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medication")
public class Medication {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;        // e.g., "Amoxicillin"

  private String dosage;      // e.g., "500mg"

  private String form;        // e.g., "Tablet", "Injection"

  private String route;       // e.g., "Oral", "IV"

  private String frequency;   // e.g., "Twice a day"

  private int durationDays;   // e.g., 7

  private boolean active = true; // soft delete flag

}
