package com.hdjunction.project.yunsang.domain.hospital.controller;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * 환자 목록 조회
     */
    @GetMapping(value = "")
    public PatientSearchResponseDto getList(@Valid @ModelAttribute PatientSearchRequestDto patientSearchRequestDto) {
        return patientService.getList(patientSearchRequestDto);
    }

    /**
     * 환자 조회
     */
    @GetMapping(value = "/{id}")
    public PatientResponseDto get(@PathVariable(value = "id") Long patientId) {
        return patientService.get(patientId);
    }
    /**
     * 환자 등록
     */
    @PostMapping(value = "")
    public void add(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        patientService.add(patientRequestDto);
    }
    /**
     * 환자 수정
     */
    @PutMapping(value = "")
    public void modify(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        patientService.modify(patientRequestDto);
    }
    /**
     * 환자 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long patientId) {
        patientService.delete(patientId);
    }
}
