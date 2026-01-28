package com.pm.hospitalservice.repository;

import com.pm.common.constant.InvitationStatus;
import com.pm.hospitalservice.entity.DoctorInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorInvitationRepository extends JpaRepository<DoctorInvitation, Long> {

  List<DoctorInvitation> findByHospitalId(long hospitalId);

  Optional<DoctorInvitation> findByDoctorIdAndHospitalIdAndStatus(long doctorId, long hospitalId,
                                                                  InvitationStatus status);

  List<DoctorInvitation> findByDoctorId(long doctorId);


}
