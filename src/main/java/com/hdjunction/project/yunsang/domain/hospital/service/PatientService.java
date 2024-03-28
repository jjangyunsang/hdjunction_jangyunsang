package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRequestDto;

import java.util.Map;

public interface PatientService {
    <T> Map<String, T> getList(PatientSearchRequestDto patientSearchRequestDto);
    PatientResponseDto get(Long patientId);
    void addPatient(PatientRequestDto patientRequestDto);
    void modifyPatient(PatientRequestDto patientRequestDto);
    void deletePatient(Long patientId);
}
