package com.hdjunction.project.yunsang.domain.hospital.repository;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRow;
import com.hdjunction.project.yunsang.domain.hospital.entity.QHospital;
import com.hdjunction.project.yunsang.domain.hospital.entity.QPatient;
import com.hdjunction.project.yunsang.domain.hospital.entity.QVisit;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepositoryCustomImpl implements PatientRepositoryCustom {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory; // TODO - Configuration DI 방식으로 변경예정.

    public PatientRepositoryCustomImpl(
            EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<PatientRow> getPatientRows(Long patientId) {
        QPatient patient = QPatient.patient;
        QHospital hospital = QHospital.hospital;
        QVisit visit = QVisit.visit;

        return queryFactory.select(
                Projections.constructor(
                        PatientRow.class
                        , patient.patientId
                        , hospital.hospitalId
                        , hospital.hospitalName
                        , hospital.medicalCareNo
                        , patient.patientName
                        , patient.patientNo
                        , patient.gender
                        , patient.birth
                        , patient.phone
                        , visit.visitId
                        , visit.registerDate
                        , visit.visitStatusCode
                ))
                .from(patient)
                .innerJoin(hospital).on(patient.hospitalId.eq(hospital.hospitalId))
                .leftJoin(visit).on(patient.patientId.eq(visit.patientId))
                .where(patient.patientId.eq(patientId))
                .fetch();
    }
}
