package com.hdjunction.project.yunsang.global.dto;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchResponseDto;
import com.hdjunction.project.yunsang.global.util.ConstantUtil;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
public class PageDto {
    private final int page; // 현재 페이지
    private final int totalPage; // 전체 페이지
    private final String pageInfo; // 11-20 (10개씩 2페이지인 경우)
    private final int limit; // 페이지내 출력수
    private final long total; // 전체 데이터수

    @Builder
    public PageDto(int page, int totalPage, String pageInfo, int limit, long total) {
        this.page = page;
        this.totalPage = totalPage;
        this.pageInfo = pageInfo;
        this.limit = limit;
        this.total = total;
    }

    public static PageDto of(Page<PatientSearchResponseDto> result) {
        Pageable pageable = result.getPageable();
        return PageDto.builder()
                .page(pageable.getPageNumber() + NumberUtils.INTEGER_ONE)
                .totalPage(result.getTotalPages())
                .pageInfo(
                        (pageable.getOffset() + NumberUtils.INTEGER_ONE) +
                        ConstantUtil.DASH +
                        (pageable.getOffset() + pageable.getPageSize())
                )
                .limit(pageable.getPageSize())
                .total(result.getTotalElements())
                .build();
    }

}
