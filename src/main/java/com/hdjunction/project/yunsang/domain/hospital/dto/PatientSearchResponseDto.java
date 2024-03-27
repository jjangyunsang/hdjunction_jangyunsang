package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.global.util.StringUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PatientSearchResponseDto {
    private final String patientName;
    private final String patientNo;
    private final String gender;
    private final String birth;
    private final String phone;
    private final String recentlyDate;

    @Builder
    public PatientSearchResponseDto(
            String patientName
            , String patientNo
            , String gender
            , String birth
            , String phone
            , LocalDateTime recentlyDate
    ) {
        this.patientName = patientName;
        this.patientNo = patientNo;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.recentlyDate = StringUtil.setStringFromLocalDateTime(recentlyDate, "yyyy-MM-dd");
    }
}
