package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.domain.hospital.domain.Visit;
import com.hdjunction.project.yunsang.global.enums.VisitStatusCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class VisitRequestDto {
    @NotNull(message = "병원ID(hospitalId)는 필수입니다.")
    @Positive(message = "병원ID(hospitalId)는 양수 입력만 가능합니다.")
    private Long hospitalId;
    @NotNull(message = "환자ID(patientId)는 필수입니다.")
    @Positive(message = "환자ID(patientId)는 양수 입력만 가능합니다.")
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
