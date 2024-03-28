package com.hdjunction.project.yunsang.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hdjunction.project.yunsang.global.enums.ResponseMessage;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * API 공통 응답구조
 */
@Getter
@Builder
public class ResponseDto<T> {
    private final Integer code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String exceptionMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public ResponseDto(
            Integer code
            , String message
            , String exceptionMessage
            , T data) {
        this.code = code;
        this.message = message;
        this.exceptionMessage = exceptionMessage;
        this.data = data;
    }

    /**
     * 응답 데이터가 있는 정상 Case
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseDto<T> success(T data) {
        return (ResponseDto<T>) ResponseDto.builder()
                .code(HttpStatus.OK.value())
                .message(ResponseMessage.OK.getMessage())
                .data(checkAndDefaultData(data))
                .build();
    }

    @SuppressWarnings(value = "unchecked")
    private static <T> T checkAndDefaultData(T data) {
        if (ObjectUtils.isEmpty(data)) {
            return (T) Map.of();
        }
        return data;
    }
    /**
     * 응답 데이터가 없는 정상 Case
     */
    public static ResponseDto<Object> success() {
        return ResponseDto.builder()
                .code(HttpStatus.OK.value())
                .message(ResponseMessage.OK.getMessage())
                .build();
    }

    /**
     * 응답 실패 Case
     */
    public static ResponseDto<Object> fail(String exceptionMessage) {
        return ResponseDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ResponseMessage.EXCEPTION.getMessage())
                .exceptionMessage(exceptionMessage)
                .build();
    }

    public static ResponseDto<Object> badRequest(String exceptionMessage) {
        return ResponseDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ResponseMessage.BAD_REQUEST.getMessage())
                .exceptionMessage(exceptionMessage)
                .build();
    }
}
