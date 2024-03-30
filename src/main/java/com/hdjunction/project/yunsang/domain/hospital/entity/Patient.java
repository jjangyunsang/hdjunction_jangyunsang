package com.hdjunction.project.yunsang.domain.hospital.entity;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "patient")
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "hospital_id")
    private Long hospitalId;
    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "patient_no")
    private String patientNo;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth")
    private String birth;
    @Column(name = "phone")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "hospital_id", insertable = false, updatable = false)
    private Hospital hospital;
    @OneToMany(mappedBy = "patient"
            , orphanRemoval = true
            , cascade = CascadeType.ALL)
    private List<Visit> visit;

    @Builder
    public Patient(
            Long patientId
            , Long hospitalId
            , String patientName
            , String patientNo
            , String gender
            , String birth
            , String phone) {
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.patientName = patientName;
        this.patientNo = patientNo;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
    }

    public void updateEntity(PatientRequestDto patientRequestDto) {
        this.hospitalId = patientRequestDto.getHospitalId();
        this.patientName = patientRequestDto.getPatientName();
        this.gender = patientRequestDto.getGender();
        this.birth = patientRequestDto.getBirth();
        this.phone = patientRequestDto.getPhone();
    }
}
