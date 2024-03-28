INSERT INTO code_group
    (code_group, code_group_name, description)
VALUES
    ('gender', '성별코드', '성별을 표시'),
    ('visit_status_code', '방문상태코드', '환자방문의 상태'),
    ('treatment_subject_code', '진료과목코드', '진료과목(내과, 안과 등)'),
    ('treatment_type_code', '진료유형코드', '진료의 유형(약처방, 검사 등)')
;

INSERT INTO code
    (code_group, code, code_name)
VALUES
    ('gender', 'M', '남'),
    ('gender', 'F', '여'),
    ('visit_status_code', '1', '방문중'),
    ('visit_status_code', '2', '종료'),
    ('visit_status_code', '3', '취소'),
    ('treatment_subject_code', '01', '내과'),
    ('treatment_subject_code', '02', '안과'),
    ('treatment_type_code', 'D', '약처방'),
    ('treatment_type_code', 'T', '검사')
;

INSERT INTO hospital
    (hospital_id, hospital_name, medical_care_no, hospital_chief)
VALUES
    (1, '서울아산병원', '11100800', '박승일'),
    (2, '분당서울대학교병원', '31100813', '송정한'),
    (3, '장윤상우리병원', '12345678', '장윤상')
;