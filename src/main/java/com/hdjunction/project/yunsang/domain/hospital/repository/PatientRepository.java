package com.hdjunction.project.yunsang.domain.hospital.repository;

import com.hdjunction.project.yunsang.domain.hospital.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom {

}
