package com.hdjunction.project.yunsang.domain.hospital.repository;

import com.hdjunction.project.yunsang.domain.hospital.dto.PatientRow;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchRequestDto;
import com.hdjunction.project.yunsang.domain.hospital.dto.PatientSearchResponseDto;
import com.hdjunction.project.yunsang.domain.hospital.entity.QHospital;
import com.hdjunction.project.yunsang.domain.hospital.entity.QPatient;
import com.hdjunction.project.yunsang.domain.hospital.entity.QVisit;
import com.hdjunction.project.yunsang.global.enums.SearchConditionEnum;
import com.hdjunction.project.yunsang.global.util.StringUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public List<PatientSearchResponseDto> getPatientSearch(PatientSearchRequestDto patientSearchRequestDto) {
        QPatient patient = QPatient.patient;
        QVisit visit = QVisit.visit;
        BooleanBuilder whereBuilder = new BooleanBuilder();
        whereBuilder.and(patient.hospitalId.eq(patientSearchRequestDto.getHospitalId()));

        Map<String, String> conditionMap = patientSearchRequestDto.toConditionMap();
        if (ObjectUtils.isNotEmpty(conditionMap.get(SearchConditionEnum.PATIENT_NAME.getKey()))) {
            whereBuilder.and(patient.patientName.like(
                    StringUtil.whereLikeFormat(conditionMap.get(SearchConditionEnum.PATIENT_NAME.getKey())))
            );

        } else if (ObjectUtils.isNotEmpty(conditionMap.get(SearchConditionEnum.PATIENT_NO.getKey()))) {
            whereBuilder.and(patient.patientNo.eq(conditionMap.get(SearchConditionEnum.PATIENT_NO.getKey())));

        } else if (ObjectUtils.isNotEmpty(conditionMap.get(SearchConditionEnum.BIRTH.getKey()))) {
            whereBuilder.and(patient.birth.eq(conditionMap.get(SearchConditionEnum.BIRTH.getKey())));
        }

        return queryFactory.select(
                Projections.constructor(
                        PatientSearchResponseDto.class
                        , patient.patientName
                        , patient.patientNo
                        , patient.gender
                        , patient.birth
                        , patient.phone
                        , visit.registerDate.max().as("recentlyDate"))
                )
                .from(patient)
                .leftJoin(visit).on(patient.patientId.eq(visit.patientId))
                .where(whereBuilder)
                .groupBy(patient.patientId)
                .orderBy(patient.patientId.desc())
                .fetch();
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
