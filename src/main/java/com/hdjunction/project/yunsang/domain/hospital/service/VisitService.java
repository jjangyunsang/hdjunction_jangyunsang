package com.hdjunction.project.yunsang.domain.hospital.service;

import com.hdjunction.project.yunsang.domain.hospital.dto.VisitRequestDto;

public interface VisitService {
    void addVisit(VisitRequestDto visitRequestDto);
    void deleteVisit(Long visitId);
}
