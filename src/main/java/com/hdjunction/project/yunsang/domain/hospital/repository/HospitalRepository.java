package com.hdjunction.project.yunsang.domain.hospital.repository;

import com.hdjunction.project.yunsang.domain.hospital.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
