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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PatientRepositoryCustomImpl implements PatientRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public PatientRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<PatientSearchResponseDto> getPatientSearch(PatientSearchRequestDto patientSearchRequestDto) {
        QPatient patient = QPatient.patient;
        QVisit visit = QVisit.visit;

        Pageable pageable = patientSearchRequestDto.toPageable();

        BooleanBuilder whereBuilder = new BooleanBuilder();
        whereBuilder.and(patient.hospitalId.eq(patientSearchRequestDto.getHospitalId()));

        Map<String, String> conditionMap = patientSearchRequestDto.toConditionMap();
        if (ObjectUtils.isNotEmpty(conditionMap.get(SearchConditionEnum.PATIENT_NAME.getCode()))) {
            whereBuilder.and(patient.patientName.like(
                    StringUtil.whereLikeFormat(conditionMap.get(SearchConditionEnum.PATIENT_NAME.getCode())))
            );

        } else if (ObjectUtils.isNotEmpty(conditionMap.get(SearchConditionEnum.PATIENT_NO.getCode()))) {
            whereBuilder.and(patient.patientNo.eq(conditionMap.get(SearchConditionEnum.PATIENT_NO.getCode())));

        } else if (ObjectUtils.isNotEmpty(conditionMap.get(SearchConditionEnum.BIRTH.getCode()))) {
            whereBuilder.and(patient.birth.eq(conditionMap.get(SearchConditionEnum.BIRTH.getCode())));
        }
        // 결과 List
        List<PatientSearchResponseDto> resultList = jpaQueryFactory.select(Projections.constructor(
                PatientSearchResponseDto.class
                , patient.patientName
                , patient.patientNo
                , patient.gender
                , patient.birth
                , patient.phone
                , visit.registerDate.max().as("recentlyVisitDate"))
                )
                .from(patient).where(whereBuilder)
                .leftJoin(visit).on(patient.patientId.eq(visit.patientId))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .groupBy(patient.patientId)
                .fetch();
        // 결과 count
        Long count = jpaQueryFactory.select(patient.patientId.count())
                .from(patient)
                .where(whereBuilder)
                .fetchOne();

        return new PageImpl<>(
                resultList
                , pageable
                , ObjectUtils.isNotEmpty(count)
                    ? count
                    : NumberUtils.LONG_ZERO);
    }

    @Override
    public List<PatientRow> getPatientRows(Long patientId) {
        QPatient patient = QPatient.patient;
        QHospital hospital = QHospital.hospital;
        QVisit visit = QVisit.visit;

        return jpaQueryFactory.select(
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
