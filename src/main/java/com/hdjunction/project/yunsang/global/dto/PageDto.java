package com.hdjunction.project.yunsang.global.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PageDto {
    private final int page; // 현재 페이지
    private final int totalPage; // 전체 페이지
    private final String pageInfo; // 11-20 (10개씩 2페이지인 경우)
    private final int limit; // 페이지내 출력수
    private final int total; // 전체 데이터수

    @Builder
    public PageDto(int page, int totalPage, String pageInfo, int limit, int total) {
        this.page = page;
        this.totalPage = totalPage;
        this.pageInfo = pageInfo;
        this.limit = limit;
        this.total = total;
    }

    public static <T> PageDto of(int page, int limit, List<T> list) {
        int totalPage = list.size() / (page * limit);
        return PageDto.builder()
                .page(page)
                .totalPage((list.size() % (page * limit)) > 0 ? totalPage + 1 : totalPage)
                .pageInfo(((page - 1) * limit + 1) + "-" + ((page - 1) * limit + limit))
                .limit(limit)
                .total(list.size())
                .build();
    }

    public <T> List<T> subList(List<T> list) {
        int start = (page-1) * limit;
        int end = Math.min(total, page * limit);
        return (total < start) ? List.of() : list.subList(start, end); // 시간복잡도 O(1)
    }
}
