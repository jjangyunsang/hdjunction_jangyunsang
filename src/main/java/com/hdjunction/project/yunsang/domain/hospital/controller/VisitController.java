package com.hdjunction.project.yunsang.domain.hospital.controller;

import com.hdjunction.project.yunsang.domain.hospital.dto.VisitRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    /**
     * 환자방문기록 등록
     */
    @PostMapping(value = "")
    public void add(@Valid @RequestBody VisitRequestDto visitRequestDto) {
        visitService.add(visitRequestDto);
    }

    /**
     * 환자방문기록 삭제
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long visitId) {
        visitService.delete(visitId);
    }
}
