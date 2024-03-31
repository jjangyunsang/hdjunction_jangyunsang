package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.domain.Patient;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRow;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRow;
import com.hdjunction.project.yunsang.domain.hospital.dto.VisitResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.repository.PatientRepository;
import com.hdjunction.project.yunsang.global.dto.PageDto;
import com.hdjunction.project.yunsang.global.enums.ErrorCodes;
import com.hdjunction.project.yunsang.global.exception.ApiException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public PatientSearchResponseDto getList(PatientSearchRequestDto patientSearchRequestDto) {
        Page<PatientSearchRow> result = patientRepository.getPatientSearch(patientSearchRequestDto);

        return PatientSearchResponseDto.builder()
                .list(result.getContent())
                .page(PageDto.of(result))
                .build();
    }

    @Transactional(transactionManager = "transactionManager", readOnly = true)
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

    @Transactional(transactionManager = "transactionManager")
    public void add(PatientRequestDto patientRequestDto) {
        patientRepository.save(patientRequestDto.toEntity());
    }

    @Transactional(transactionManager = "transactionManager")
    public void modify(PatientRequestDto patientRequestDto) {
        if (patientRequestDto.hasNotPatientId()) throw new ApiException(ErrorCodes.NOT_FOUNT_PATIENT_ID);

        Patient patient = patientRepository.findById(patientRequestDto.getPatientId())
                .orElseThrow(() -> new ApiException(ErrorCodes.NOT_FOUND_PATIENT_ENTITY));
        patient.updateEntity(patientRequestDto); // Dirty Checking Update
    }

    @Transactional(transactionManager = "transactionManager")
    public void delete(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ApiException(ErrorCodes.NOT_FOUND_PATIENT_ENTITY));
        patientRepository.delete(patient);
    }
}
