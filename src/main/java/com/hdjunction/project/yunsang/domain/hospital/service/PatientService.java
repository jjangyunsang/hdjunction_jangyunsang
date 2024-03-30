package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchResponseDto;
import com.hdjunction.project.yunsang.global.dto.ListDto;

public interface PatientService {
    ListDto<PatientSearchResponseDto> getList(PatientSearchRequestDto patientSearchRequestDto);
    PatientResponseDto get(Long patientId);
    void addPatient(PatientRequestDto patientRequestDto);
    void modifyPatient(PatientRequestDto patientRequestDto);
    void deletePatient(Long patientId);
}
