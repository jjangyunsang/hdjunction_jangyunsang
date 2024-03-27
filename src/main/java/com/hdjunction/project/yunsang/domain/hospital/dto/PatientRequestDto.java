package com.hdjunction.project.yunsang.domain.hospital.dto;

import com.hdjunction.project.yunsang.domain.hospital.entity.Patient;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

@Getter
@NoArgsConstructor
@ToString
public class PatientRequestDto {
    @Positive(message = "환자ID(patientId)는 양수 입력만 가능합니다.")
    private Long patientId;
    @Positive(message = "병원ID(hospitalId)는 양수 입력만 가능합니다.")
    private Long hospitalId;
    @Size(max = 45, message = "환자명(patientName)은 최대 45자까지 입력이 가능합니다.")
    private String patientName;
    private String patientNo;
    @Pattern(regexp = "M|W", message = "성별(gender) 값이 올바르지 않습니다.")
    private String gender;
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[1-2]\\d|3[0-1])$", message = "생년월일(birth) 값이 올바르지 않습니다. (ex) 19000101)")
    private String birth;
    @Pattern(regexp = "^01[016-9]\\d{7,8}$", message = "휴대전화번호(phone) 값이 올바르지 않습니다. (ex) 01012345678)")
    private String phone;

    @Builder
    public PatientRequestDto(
            Long patientId
            , Long hospitalId
            , String patientName
            , String patientNo
            , String gender
            , String birth
            , String phone
    ) {
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.patientName = patientName;
        this.patientNo = patientNo;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
    }

    public Patient toEntity() {
        return Patient.builder()
                .patientId(patientId)
                .hospitalId(hospitalId)
                .patientName(patientName)
                .patientNo(createPatientNo())
                .gender(gender)
                .birth(birth)
                .phone(phone)
                .build();
    }

    public boolean hasNotPatientId() {
        return ObjectUtils.isEmpty(patientId);
    }
    // TODO - 생성 방법 고민 필요.
    /**
     * 환자번호 생성
     */
    private String createPatientNo() {
        return hospitalId + "-" + birth;
    }
}
