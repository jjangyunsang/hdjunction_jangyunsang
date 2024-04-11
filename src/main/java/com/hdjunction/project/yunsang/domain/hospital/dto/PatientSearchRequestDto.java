package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.global.enums.SearchConditionEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Getter
public class PatientSearchRequestDto {
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_LIMIT = 10;
    @Positive(message = "페이지번호는 1부터 입력이 가능합니다.")
    private final Integer page;
    private final Integer limit;
    @NotNull(message = "병원ID는 필수입니다.")
    private final Long hospitalId;
    private final String searchField;
    private final String searchValue;

    public PatientSearchRequestDto(
            Integer page
            , Integer limit
            , Long hospitalId
            , String searchField
            , String searchValue
    ) {
        this.page = ObjectUtils.isNotEmpty(page) ? page : DEFAULT_PAGE;
        this.limit = ObjectUtils.isNotEmpty(limit) ? limit : DEFAULT_LIMIT;
        this.hospitalId = hospitalId;
        this.searchField = searchField;
        this.searchValue = searchValue;
    }

    public Map<String, String> toConditionMap() {
        Map<String, String> conditionMap = SearchConditionEnum.toDefaultMapByKey();
        if (ObjectUtils.isNotEmpty(searchField)) {
            conditionMap.computeIfPresent(searchField, (k, v) -> k);
        }

        return conditionMap;
    }

    public Pageable toPageable() {
        return PageRequest.of(page-1, limit);
    }
}
