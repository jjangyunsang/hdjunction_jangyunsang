package com.hdjunction.project.yunsang.global.enums;

import com.hdjunction.project.yunsang.global.exception.ApiException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MAN("M", "남성")
    , WOMAN("W", "여성")
    , UNKNOWN("UNKNOWN", "모름")
    ;

    private final String code;
    private final String name;

    Gender(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String findName(String code) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new ApiException("성별 인자값이 올바르지 않습니다."))
                .getName();
    }
}
