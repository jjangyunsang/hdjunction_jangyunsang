package com.hdjunction.project.yunsang.domain.hospital.controller;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.service.PatientService;
import com.hdjunction.project.yunsang.global.dto.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseDto<Object> getList() {
        return null;
    }

    /**
     * 환자 조회
     */
    @GetMapping(value = "/{id}")
    public ResponseDto<PatientResponseDto> get(@PathVariable(value = "id") Long patientId) {
        return ResponseDto.success(patientService.get(patientId));
    }
    /**
     * 환자 등록
     */
    @PostMapping(value = "")
    public ResponseDto<Object> add(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        patientService.addPatient(patientRequestDto);
        return ResponseDto.success();
    }
    /**
     * 환자 수정
     */
    @PutMapping(value = "")
    public ResponseDto<Object> modify(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        patientService.modifyPatient(patientRequestDto);
        return ResponseDto.success();
    }
    /**
     * 환자 삭제
     */
    @DeleteMapping(value = "/{id}")
    public ResponseDto<Object> delete(@PathVariable(value = "id") Long patientId) {
        patientService.deletePatient(patientId);
        return ResponseDto.success();
    }
}
