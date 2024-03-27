package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientResponseDto;

public interface PatientService {
    void getList();
    PatientResponseDto get(Long patientId);
    void addPatient(PatientRequestDto patientRequestDto);
    void modifyPatient(PatientRequestDto patientRequestDto);
    void deletePatient(Long patientId);
}
