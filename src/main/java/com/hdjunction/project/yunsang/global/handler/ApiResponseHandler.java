package com.hdjunction.project.yunsang.global.handler;

import com.hdjunction.project.yunsang.global.dto.ResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(
        basePackages = "com.hdjunction.project.yunsang.domain.hospital.controller"
)
public class ApiResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 컨트롤러의 반환타입이 객체일 때는 직렬화를 위해 MappingJackson2HttpMessageConverter를 사용하게 되므로, 해당 컨버터가 사용될 때만 ResponseBodyAdvice를 거치도록 변경한다.
        // 만약 리턴타입이 문자열인 경우에는 그대로 Body에 반환될 것이고, DTO와 같은 객체일 때는 ResponseDto로 감싸져서 반환된다.
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(
            Object body
            , MethodParameter returnType
            , MediaType selectedContentType
            , Class<? extends HttpMessageConverter<?>> selectedConverterType
            , ServerHttpRequest request
            , ServerHttpResponse response
    ) {
        return ResponseDto.success(body);
    }
}
