package com.hdjunction.project.yunsang.domain.hospital.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PatientResponseDto {
    private Long patientId;
    private Long hospitalId;
    private String hospitalName;
    private String medicalCareNo;
    private String patientName;
    private String patientNo;
    private String gender;
    private String birth;
    private String phone;
    private List<VisitResponseDto> visits;

    @Builder
    public PatientResponseDto(
            Long patientId
            , Long hospitalId
            , String hospitalName
            , String medicalCareNo
            , String patientName
            , String patientNo
            , String gender
            , String birth
            , String phone
            , List<VisitResponseDto> visits
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
        this.visits = visits;
    }

    public void updateVisits(List<VisitResponseDto> visits) {
        this.visits = visits;
    }

}
