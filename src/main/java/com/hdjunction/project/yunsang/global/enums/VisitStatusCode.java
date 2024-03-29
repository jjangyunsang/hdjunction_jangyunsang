package com.hdjunction.project.yunsang.global.enums;

import lombok.Getter;

@Getter
public enum VisitStatusCode {
    VISIT("1")
    , VISIT_END("2")
    , VISIT_CANCEL("3")
    ;

    private final String code;

    VisitStatusCode(String code) {
        this.code = code;
    }
}
