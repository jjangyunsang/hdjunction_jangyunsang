package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.global.enums.Gender;
import com.hdjunction.project.yunsang.global.util.StringUtil;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PatientRow {
    private final Long patientId;
    private final Long hospitalId;
    private final String hospitalName;
    private final String medicalCareNo;
    private final String patientName;
    private final String patientNo;
    private final String gender;
    private final String birth;
    private final String phone;
    private final Long visitId;
    private final LocalDateTime registerDate;
    private final String visitStatusCode;

    public PatientRow(
            Long patientId
            , Long hospitalId
            , String hospitalName
            , String medicalCareNo
            , String patientName
            , String patientNo
            , String gender
            , String birth
            , String phone
            , Long visitId
            , LocalDateTime registerDate
            , String visitStatusCode
    ) {
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.medicalCareNo = medicalCareNo;
        this.patientName = patientName;
        this.patientNo = patientNo;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.visitId = visitId;
        this.registerDate = registerDate;
        this.visitStatusCode = visitStatusCode;
    }

    public PatientResponseDto toPatientResponseDto() {
        return PatientResponseDto.builder()
                .patientId(patientId)
                .hospitalId(hospitalId)
                .hospitalName(hospitalName)
                .medicalCareNo(medicalCareNo)
                .patientName(patientName)
                .patientNo(patientNo)
                .gender(Gender.of(gender).getName())
                .birth(StringUtil.birthFormat(birth))
                .phone(phone)
                .build();
    }

    public VisitResponseDto toVisitResponseDto() {
        return VisitResponseDto.builder()
                .visitId(visitId)
                .registerDate(registerDate)
                .visitStatusCode(visitStatusCode)
                .build();
    }
}
