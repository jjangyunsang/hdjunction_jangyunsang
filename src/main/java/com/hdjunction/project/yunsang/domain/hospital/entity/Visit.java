package com.hdjunction.project.yunsang.domain.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "visit")
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long visitId;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "register_date")
    private LocalDateTime registerDate;
    @Column(name = "visit_status_code")
    private String visitStatusCode;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    private Patient patient;
}
