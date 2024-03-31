package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.dto.VisitRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.domain.Visit;
import com.hdjunction.project.yunsang.domain.hospital.repository.VisitRepository;
import com.hdjunction.project.yunsang.global.enums.ErrorCodes;
import com.hdjunction.project.yunsang.global.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Transactional(transactionManager = "transactionManager")
    public void add(VisitRequestDto visitRequestDto) {
        visitRepository.save(visitRequestDto.toEntity());
    }

    @Transactional(transactionManager = "transactionManager")
    public void modify(VisitRequestDto visitRequestDto) {
        Visit visit = visitRepository.findById(visitRequestDto.getVisitId())
                .orElseThrow(() -> new ApiException(ErrorCodes.NOT_FOUNT_VISIT_ENTITY));
        visit.updateEntity(visitRequestDto); // Dirty Checking Update
    }

    @Transactional(transactionManager = "transactionManager")
    public void delete(Long visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new ApiException(ErrorCodes.NOT_FOUNT_VISIT_ENTITY));
        visitRepository.delete(visit);
    }
}
