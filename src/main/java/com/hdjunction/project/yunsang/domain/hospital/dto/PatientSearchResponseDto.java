package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.global.dto.PageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PatientSearchResponseDto {
    private List<PatientSearchRow> list;
    private PageDto page;

    @Builder
    public PatientSearchResponseDto(
            List<PatientSearchRow> list
            , PageDto page
    ) {
        this.list = list;
        this.page = page;
    }
}
