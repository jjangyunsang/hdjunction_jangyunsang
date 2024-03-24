package com.hdjunction.project.yunsang.domain.hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "patient")
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;
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
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    @OneToMany(mappedBy = "patient", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Visit> visit = new ArrayList<>();

}
