package com.hdjunction.project.yunsang.global.enums;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    NOT_FOUNT_PATIENT_ID("환자ID가 존재하지 않습니다.")
    , NOT_FOUND_PATIENT_ENTITY("환자 정보가 없습니다.")
    , NOT_FOUNT_VISIT_ENTITY("환자 방문정보가 없습니다.")
    ;

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }
}
