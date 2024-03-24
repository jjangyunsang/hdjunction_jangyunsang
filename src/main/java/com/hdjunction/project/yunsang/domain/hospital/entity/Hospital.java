package com.hdjunction.project.yunsang.domain.hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "hospital")
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long hospitalId;
    @Column(name = "hospital_name")
    private String hospitalName;
    @Column(name = "medical_care_no")
    private String medicalCareNo;
    @Column(name = "hospital_chief")
    private String hospitalChief;
    @OneToMany(mappedBy = "hospital", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Patient> patient = new ArrayList<>();
    @OneToMany(mappedBy = "hospital", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Visit> visit = new ArrayList<>();
}
