## 프로젝트 설명
유저는 유지보수 받고 싶은 물품에 대해 접수를,

클라이언트는 유저의 접수를 신속히 받고 응대 및 마무리 까지의

서비스 로직을 돕는 유지보수 접수 프로젝트 입니다.

## 사용 기술
- 인텔리제이
- Java 11
- MySql
- Redis
- Embedded Redis

## 프로젝트 기능
- [x] 회원가입
  - 필요 정보
    - username
    - password
    - name
    - address
    - mobile
    - mail
    - role
  - [x] 일반 유저 권한 
  - [x] 대표 권한(MANAGER, 파트너 계정 생성 및 리뷰 확인 가능)
  - [x] 파트너(내근직) 권한 (유지보수 접수 사원)
  - [x] 파트너(외근직) 권한 (유지보수 방문 사원)
- [x] 로그인
  - 필요 정보
    - username
    - password
- [x] 유지보수 접수 (유저 권한)
  - 필요 정보
    - account (계정)
    - item (접수 물품)
    - purchaseDate (구매일)
    - issueDescription (접수 내용)
- [x] 유지보수 접수 취소
  - 필요 정보
    - cancelResaon (취소 이유)
- [x] 유지보수 접수 확정 (파트너(내근직) 권한)
  - [x] 접수 건에 대한 상태 확정
  - [x] 방문 예정 시간 등록
  - [x] 방문 확정 시 유저에게 안내 문자 발송
    - 파트너 정보
      - name
      - mobile
    - 방문 예정 시간
    - 파트너(외근직)의 실제 방문 시간 확정을 위한 URL
      - 해당 URL을 누른 시간이 유지보수 조회 시 방문 완료 시간으로 저장 됨
  - [x] 방문 확정 시 파트너(외근직)에게 안내 문자 발송
      - 유저 정보
        - name
        - mobile
        - address
      - 접수 기기, 구매 일자, 접수 내용
      - 방문 예정 시간
      - 특이사항
- [x] 리포트 등록 (파트너(외근직) 권한)
  - 처리 사진
  - 처리 내역(Report Detail)
- [X] 리뷰 등록 (유저 권한)
  - 별점 리뷰
  - 텍스트 리뷰
- [X] 리뷰 조회 (관리자 권한(MANAGER))
  - 전체 리뷰 조회
  - 개별 리뷰 상세 조회
  - 직회
## ERD
![maintenance ERD 수정본_최종](https://github.com/M-subray/maintenance/assets/144686741/6f41ea34-249e-412c-8ed9-d7f4130e6b00)
