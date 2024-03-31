# 에이치디정션-장윤상 / 온라인과제 프로젝트

안녕하세요, 지원자 장윤상입니다. 전달 요구사항에 따른 과제 프로젝트입니다.

- 사용기술
  - Java 17
  - SpringBoot 3.2.4
- 사용 Dependency
  - 기본 과제 제시 Dependency
    - Spring Web
    - Spring Data JPA + QueryDSL
    - H2 Database
  - 추가 Dependency
    - Spring Validation
    - Apache Common Lang3
    - Lombok
- github link
  - https://github.com/jjangyunsang/hdjunction_jangyunsang
- h2 database
  - 접속 URL: http://localhost/h2-console
    - Driver Class: org.h2.Driver
    - JDBC URL: jdbc:h2:mem:hdjunction
    - User Name: sa
    - Password: (편의상 입력하지 않도록함)
- spring rest docs
  - 반영이 잘 안되어서 feature/HDJUNCTION-7 브랜치에 남겨두었습니다.
  - http://localhost/docs/index.html

- API 목록
  - src/main/resources/http/restful_api.http 파일로 확인가능

  | API명      | Method | URL                        |
  |-----------|--------|----------------------------|
  | 환자목록 조회   | GET    | /patient                   |
  | 환자정보 조회   | GET    | /patient/{patientId}       |
  | 환자정보 등록   | POST   | /patient                   |
  | 환자정보 수정   | PUT    | /patient                   |
  | 환자정보 삭제   | DELETE | /patient/{patientId}       |
  | 환자방문정보 등록 | POST   | /visit-patient             |
  | 환자방문정보 삭제 | DELETE | /visit-patient/{patientId} |