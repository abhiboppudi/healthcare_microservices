package com.pm.doctorservice.entity;

import com.pm.common.dto.PrescriptionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prescription")
public class Prescription {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long doctorId;

  private Long patientId;

  private Long appointmentId;

  private Long hospitalId;

  private LocalDate issuedDate;

  private LocalDate validUntil;

  @Enumerated(EnumType.STRING)
  private PrescriptionStatus status;

  @ManyToMany
  @JoinTable(
      name = "prescription_medications",
      joinColumns = @JoinColumn(
          name = "prescription_id",        // column in join table
          referencedColumnName = "id"      // PK column in prescription table
      ),
      inverseJoinColumns = @JoinColumn(
          name = "medication_id",          // column in join table
          referencedColumnName = "id"      // PK column in medication table
      )
  )
  private List<Medication> medications;

  private String notes;
}
