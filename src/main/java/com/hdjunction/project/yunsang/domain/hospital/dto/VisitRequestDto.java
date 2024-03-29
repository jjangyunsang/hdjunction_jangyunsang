package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.domain.hospital.entity.Visit;
import com.hdjunction.project.yunsang.global.enums.VisitStatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class VisitRequestDto {
    private Long hospitalId;
    private Long patientId;
    private LocalDateTime registerDate;
    private String visitStatusCode;

    public Visit toEntity() {
        return Visit.builder()
                .hospitalId(hospitalId)
                .patientId(patientId)
                .registerDate(LocalDateTime.now())
                .visitStatusCode(VisitStatusCode.VISIT.getCode())
                .build();
    }
}
