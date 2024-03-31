package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.global.enums.Gender;
import com.hdjunction.project.yunsang.global.util.StringUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PatientSearchRow {
    private final String patientName;
    private final String patientNo;
    private final String gender;
    private final String birth;
    private final String phone;
    private final String recentlyVisitDate;

    @Builder
    public PatientSearchRow(
            String patientName
            , String patientNo
            , String gender
            , String birth
            , String phone
            , LocalDateTime recentlyVisitDate
    ) {
        this.patientName = patientName;
        this.patientNo = patientNo;
        this.gender = Gender.of(gender).getName();
        this.birth = StringUtil.birthFormat(birth);
        this.phone = phone;
        this.recentlyVisitDate = StringUtil.setStringFromLocalDateTime(recentlyVisitDate, "yyyy-MM-dd");
    }
}
