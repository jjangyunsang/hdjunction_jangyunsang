package com.hdjunction.project.yunsang.global.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ListDto<T> {
    private final List<T> list;
    private final PageDto page;

    public ListDto(List<T> list, PageDto page) {
        this.list = list;
        this.page = page;
    }
}
