package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.global.enums.SearchConditionEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;

@Getter
@Setter
public class PatientSearchRequestDto {
    private Integer page;
    private Integer limit;
    @NotNull(message = "병원ID는 필수입니다.")
    private Long hospitalId;
    private String searchField;
    private String searchValue;

    public PatientSearchRequestDto(
            Integer page
            , Integer limit
            , Long hospitalId
            , String searchField
            , String searchValue
    ) {
        this.page = 1; // default: 1
        this.limit = 10; // default: 10
        this.hospitalId = hospitalId;
        this.searchField = searchField;
        this.searchValue = searchValue;
    }

    public Map<String, String> toConditionMap() {
        Map<String, String> conditionMap = SearchConditionEnum.toDefaultMapByKey();
        if (ObjectUtils.isNotEmpty(searchField) || conditionMap.containsKey(searchField)) {
            conditionMap.put(searchField, searchValue);
        }

        return conditionMap;
    }
}
