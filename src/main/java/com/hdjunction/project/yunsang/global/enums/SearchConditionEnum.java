package com.hdjunction.project.yunsang.global.enums;

import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum SearchConditionEnum {
    PATIENT_NAME("patientName", "이름")
    , PATIENT_NO("patientNo", "환자등록번호")
    , BIRTH("birth", "생년월일")
    ;

    private final String code;
    private final String name;

    SearchConditionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Map<String, String> toDefaultMapByKey() {
        Map<String, String> map = new HashMap<>();
        for (SearchConditionEnum searchEnum : SearchConditionEnum.values()) {
            map.put(searchEnum.getCode(), null);
        }
        return map;
    }
}
