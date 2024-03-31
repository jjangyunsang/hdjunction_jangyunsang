package com.hdjunction.project.yunsang.global.enums;

import lombok.Getter;

@Getter
public enum ResponseMessage {
    OK("정상 처리되었습니다.")
    , BAD_REQUEST("유효하지 않은 값입니다.")
    , EXCEPTION("서버 오류가 발생하였습니다.");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }
}
