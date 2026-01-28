package com.pm.appointmentservice.repository;

import com.pm.appointmentservice.constants.AppointmentStatus;
import com.pm.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  public List<Appointment> findByPatientIdAndStatusIn(long patientId, List<AppointmentStatus> statuses);

  public Optional<Appointment> findByAppointmentIdAndStatusIn(long appointmentId, List<AppointmentStatus> statuses);

  public List<Appointment> findAppointmentByPatientId(long patientId);

  public List<Appointment> findAppointmentByDoctorId(long doctorId);

}