package com.hdjunction.project.yunsang.docs.hospital;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.service.PatientService;
import com.hdjunction.project.yunsang.global.dto.ListDto;
import com.hdjunction.project.yunsang.global.dto.PageDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
class PatientControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    private final PatientService patientService = Mockito.mock(PatientService.class);

    @DisplayName("환자 목록 조회 API")
    @Test
    void getList() throws Exception {
        // given
        given(patientService.getList(any())).willReturn(new ListDto<>(
                List.of(PatientSearchResponseDto.builder()
                        .patientName("장윤상")
                        .patientNo("3-1")
                        .gender("M")
                        .birth("19900413")
                        .phone("01090101247")
                        .recentlyVisitDate(LocalDateTime.now())
                        .build()),
                PageDto.builder()
                        .page(1)
                        .totalPage(1)
                        .pageInfo("1-10")
                        .limit(10)
                        .total(1)
                        .build()
        ));

        // when & then
        mockMvc.perform(
                RestDocumentationRequestBuilders.get("/patient")
                        .param("hospitalId", "3")
                        .param("searchField", "patientName")
                        .param("searchValue", "장윤상")
                        .param("page", "1")
                        .param("limit", "10")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-patient",
                                preprocessRequest(prettyPrint())
                                , preprocessResponse(prettyPrint())
                                , queryParameters(
                                        parameterWithName("hospitalId").description("병원ID")
                                        , parameterWithName("searchField").description("검색조건")
                                        , parameterWithName("searchValue").description("검색내용")
                                        , parameterWithName("page").description("페이지번호")
                                        , parameterWithName("limit").description("요청페이지수")
                                )
                                , responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답코드")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("응답메시지")
                                        , fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답메시지")
                                        , fieldWithPath("data.list[0].patientName").type(JsonFieldType.STRING).description("환자명")
                                        , fieldWithPath("data.list[0].patientNo").type(JsonFieldType.STRING).description("환자번호")
                                        , fieldWithPath("data.list[0].gender").type(JsonFieldType.STRING).description("성별")
                                        , fieldWithPath("data.list[0].birth").type(JsonFieldType.STRING).description("생년월일")
                                        , fieldWithPath("data.list[0].phone").type(JsonFieldType.STRING).description("전화번호")
                                        , fieldWithPath("data.list[0].recentlyVisitDate").type(JsonFieldType.STRING).description("최근 방문기록")
                                        , fieldWithPath("data.page").type(JsonFieldType.OBJECT).description("페이지정보")
                                        , fieldWithPath("data.page.page").type(JsonFieldType.NUMBER).description("현재 페이지")
                                        , fieldWithPath("data.page.totalPage").type(JsonFieldType.NUMBER).description("전체 페이지")
                                        , fieldWithPath("data.page.pageInfo").type(JsonFieldType.STRING).description("페이지 정보 11-20 (10개씩 2페이지인 경우)")
                                        , fieldWithPath("data.page.limit").type(JsonFieldType.NUMBER).description("페이지내 출력수")
                                        , fieldWithPath("data.page.total").type(JsonFieldType.NUMBER).description("전체 데이터수")
                        )
                    )
                );
    }
}
