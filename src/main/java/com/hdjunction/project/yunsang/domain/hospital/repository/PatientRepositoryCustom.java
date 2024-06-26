package com.hdjunction.project.yunsang.domain.hospital.repository;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRow;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRow;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientRepositoryCustom {
    Page<PatientSearchRow> getPatientSearch(PatientSearchRequestDto patientSearchRequestDto);
    List<PatientRow> getPatientRows(Long patientId);
}
