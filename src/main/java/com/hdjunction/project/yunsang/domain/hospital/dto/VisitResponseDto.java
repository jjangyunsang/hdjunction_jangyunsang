package com.hdjunction.project.yunsang.domain.hospital.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class VisitResponseDto {
    private Long visitId;
    private LocalDateTime registerDate;
    private String visitStatusCode;

    @Builder
    public VisitResponseDto(
            Long visitId
            , LocalDateTime registerDate
            , String visitStatusCode
    ) {
        this.visitId = visitId;
        this.registerDate = registerDate;
        this.visitStatusCode = visitStatusCode;
    }
}
