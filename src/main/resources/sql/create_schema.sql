CREATE SCHEMA hd_junction;
USE hd_junction;

CREATE TABLE code_group (
    code_group VARCHAR(30) PRIMARY KEY COMMENT '코드그룹',
    code_group_name VARCHAR(10) NOT NULL COMMENT '코드그룹명',
    description VARCHAR(45) NOT NULL COMMENT '설명'
);
COMMENT ON TABLE code_group IS '코드그룹';

CREATE TABLE code (
    code_group VARCHAR(30) NOT NULL COMMENT '코드그룹',
    code VARCHAR(10) PRIMARY KEY COMMENT '코드',
    code_name VARCHAR(10) NOT NULL COMMENT '코드명',
    CONSTRAINT fk_code_code_group FOREIGN KEY (code_group) REFERENCES code_group(code_group)
);
COMMENT ON TABLE code IS '코드';

CREATE TABLE hospital (
    hospital_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '병원ID',
    hospital_name VARCHAR(45) NOT NULL COMMENT '병원명',
    medical_care_no VARCHAR(20) NOT NULL COMMENT '요양기관번호',
    hospital_chief VARCHAR(10) NOT NULL COMMENT '병원장명',
    CHECK ( hospital_id >= 0 )
);
COMMENT ON TABLE hospital IS '병원';

CREATE TABLE patient (
    patient_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환자ID',
    hospital_id BIGINT NOT NULL COMMENT '병원ID',
    patient_name VARCHAR(45) NOT NULl COMMENT '환자명',
    patient_no VARCHAR(13) NOT NULL COMMENT '환자등록번호',
    gender VARCHAR(10) NOT NULl COMMENT '성별코드',
    birth VARCHAR(10) NULL COMMENT '생년월일',
    phone VARCHAR(20) NULL COMMENT '휴대전화번호',
    CONSTRAINT fk_patient_hospital_id FOREIGN KEY (hospital_id) REFERENCES hospital(hospital_id),
    CHECK ( patient_id >= 0 ),
    CHECK ( hospital_id >= 0 )
);
COMMENT ON TABLE patient IS '환자';
-- 검색 대상 index 추가
CREATE INDEX idx_patient_patient_name ON patient (patient_name);
CREATE INDEX idx_patient_patient_no ON patient (patient_no);
CREATE INDEX idx_patient_birth ON patient (birth);

CREATE TABLE visit (
    visit_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환자방문ID',
    hospital_id BIGINT NOT NULL COMMENT '병원ID',
    patient_id BIGINT NOT NULL COMMENT '환자ID',
    register_date DATETIME NOT NULL COMMENT '접수일시',
    visit_status_code VARCHAR(10) NOT NULL COMMENT '방문상태코드',
    CONSTRAINT fk_patient_visit_hospital_id FOREIGN KEY (hospital_id) REFERENCES hospital(hospital_id),
    CONSTRAINT fk_patient_visit_patient_id FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
    CHECK ( visit_id >= 0 ),
    CHECK ( hospital_id >= 0 ),
    CHECK ( patient_id >= 0 )
);
COMMENT ON TABLE visit IS '환자방문';