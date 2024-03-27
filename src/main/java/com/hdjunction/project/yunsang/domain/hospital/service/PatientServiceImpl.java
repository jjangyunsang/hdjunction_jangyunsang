package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRow;
import com.hdjunction.project.yunsang.domain.hospital.dto.VisitResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.entity.Patient;
import com.hdjunction.project.yunsang.domain.hospital.repository.PatientRepository;
import com.hdjunction.project.yunsang.global.enums.ErrorCodes;
import com.hdjunction.project.yunsang.global.exception.ApiException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void getList() {

    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDto get(Long patientId) {
        List<PatientRow> patientRows = patientRepository.getPatientRows(patientId);
        if (ObjectUtils.isEmpty(patientRows)) throw new ApiException(ErrorCodes.NOT_FOUNT_PATIENT_ID);

        PatientResponseDto patientResponseDto = patientRows.get(NumberUtils.INTEGER_ZERO).toPatientResponseDto();
        List<VisitResponseDto> visitResponseDtos = patientRows.stream()
                .sorted(Comparator.comparing(PatientRow::getVisitId).reversed())
                .map(PatientRow::toVisitResponseDto)
                .toList();

        patientResponseDto.updateVisits(visitResponseDtos);
        return patientResponseDto;
    }

    @Override
    @Transactional
    public void addPatient(PatientRequestDto patientRequestDto) {
        patientRepository.save(patientRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void modifyPatient(PatientRequestDto patientRequestDto) {
        if (patientRequestDto.hasNotPatientId()) throw new ApiException(ErrorCodes.NOT_FOUNT_PATIENT_ID);

        Patient patient = patientRepository.findById(patientRequestDto.getPatientId())
                .orElseThrow(() -> new ApiException(ErrorCodes.NOT_FOUND_PATIENT_ENTITY));
        patient.updateEntity(patientRequestDto);

        patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ApiException(ErrorCodes.NOT_FOUND_PATIENT_ENTITY));
        patientRepository.delete(patient);
    }
}
