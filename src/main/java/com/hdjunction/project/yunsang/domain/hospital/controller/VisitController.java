package com.hdjunction.project.yunsang.domain.hospital.controller;

import com.hdjunction.project.yunsang.domain.hospital.dto.VisitRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.service.VisitService;
import com.hdjunction.project.yunsang.global.dto.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visit-patient")
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    /**
     * 환자방문기록 등록
     */
    @PostMapping(value = "")
    public ResponseDto<Object> add(@Valid @RequestBody VisitRequestDto visitRequestDto) {
        visitService.addVisit(visitRequestDto);
        return ResponseDto.success();
    }

    /**
     * 환자방문기록 삭제
     */
    @DeleteMapping(value = "/{id}")
    public ResponseDto<Object> delete(@PathVariable(value = "id") Long visitId) {
        visitService.deleteVisit(visitId);
        return ResponseDto.success();
    }
}
