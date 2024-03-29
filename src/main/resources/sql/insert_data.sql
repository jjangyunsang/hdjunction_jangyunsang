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

INSERT INTO patient VALUES (1, 3, '장윤상', '등록번호1', 'M', '19900413', '01090101247');
INSERT INTO patient VALUES (2, 3, '홍길동', '등록번호2', 'M', '20010112', '01012345678');
INSERT INTO patient VALUES (3, 3, '성춘향', '등록번호3', 'W', '19940101', '01087654321');
INSERT INTO patient VALUES (4, 3, '이몽룡', '등록번호3', 'M', '19980912', '01043218765');
INSERT INTO patient VALUES (5, 3, '손오공', '등록번호3', 'M', '20001231', '01019900413');
INSERT INTO patient VALUES (6, 3, '사오정', '등록번호3', 'M', '20121130', '01099998888');
INSERT INTO patient VALUES (7, 3, '김초롱', '등록번호3', 'W', '19911108', '01077776666');
ALTER TABLE patient ALTER COLUMN patient_id RESTART WITH 8;

INSERT INTO visit VALUES (1, 3, 1, CURRENT_TIMESTAMP, '2');
INSERT INTO visit VALUES (2, 3, 1, CURRENT_TIMESTAMP, '2');
INSERT INTO visit VALUES (3, 3, 2, CURRENT_TIMESTAMP, '2');
INSERT INTO visit VALUES (4, 3, 2, CURRENT_TIMESTAMP, '2');
INSERT INTO visit VALUES (5, 3, 2, CURRENT_TIMESTAMP, '2');
INSERT INTO visit VALUES (6, 3, 3, CURRENT_TIMESTAMP, '3');
INSERT INTO visit VALUES (7, 3, 3, CURRENT_TIMESTAMP, '3');
INSERT INTO visit VALUES (8, 3, 3, CURRENT_TIMESTAMP, '2');
INSERT INTO visit VALUES (9, 3, 3, CURRENT_TIMESTAMP, '2');
INSERT INTO visit VALUES (10, 3, 3, CURRENT_TIMESTAMP, '1');
ALTER TABLE visit ALTER COLUMN visit_id RESTART WITH 11;