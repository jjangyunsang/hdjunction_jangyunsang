package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.dto.VisitRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.entity.Visit;
import com.hdjunction.project.yunsang.domain.hospital.repository.VisitRepository;
import com.hdjunction.project.yunsang.global.enums.ErrorCodes;
import com.hdjunction.project.yunsang.global.exception.ApiException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    @Transactional
    public void addVisit(VisitRequestDto visitRequestDto) {
        visitRepository.save(visitRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteVisit(Long visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new ApiException(ErrorCodes.NOT_FOUNT_VISIT_ENTITY));
        visitRepository.delete(visit);
    }
}
