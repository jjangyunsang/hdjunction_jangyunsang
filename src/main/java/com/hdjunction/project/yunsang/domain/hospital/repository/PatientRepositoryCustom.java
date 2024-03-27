package com.hdjunction.project.yunsang.domain.hospital.repository;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRow;

import java.util.List;

public interface PatientRepositoryCustom {
    List<PatientRow> getPatientRows(Long patientId);
}
