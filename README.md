# BankSystemServer

## 프로젝트 소개
* **Tech** : Spring Boot 3.2.5, Spring data JPA, Spring Security, QueryDSL
* **Server** : Amazon EC2
* **Database** : PostgreSQL
* **Language** : Java
* **ETC** : Lombok, Jenkins

-------------------------------

## 신규 버전 배포 전략

<img width="900" alt="Screenshot 2024-07-21 at 10 06 25 PM" src="https://github.com/user-attachments/assets/5b2c185a-599f-403d-87e9-fc7415b6e811">

----------------------------------

## 시스템 아키텍처
<img width="1360" alt="Screenshot 2024-08-22 at 11 57 51 PM" src="https://github.com/user-attachments/assets/faccd8a7-b605-4d39-aaa3-c41ebbed33c7">


-----------------------------------

## ERD Diagram
<img width="1093" alt="Screenshot 2024-08-23 at 12 01 42 AM" src="https://github.com/user-attachments/assets/e01d91b1-4909-4dcd-88d5-0a75b90f7e38">

-----------------------------------

## API 명세서

|Domain|Method|Description|Authentication|
|----------|-----|-----|-----|
|/api/user/signup|POST|회원 가입 API|X(토큰 없이도 가능)|
|/api/user/login|POST|로그인 API|X|
|/api/user/{userId}|GET|유저 정보 조회 API|O(토큰 있어야 가능|
|/api/user/{userId}|POST|유저 정보 수정 API|O|
|/api/account/search/myaccount/{userId}|GET|나의 통장 계좌 리스트 조회 API|O|
|/api/account/create/account|POST|계좌 생성 API|O|
|/api/account/delete/myaccount|DELETE|계좌 삭제 API|O|
|/api/account/myaccount/deposit|PUT|통장 입금 API|O|
|/api/account/myaccount/withdraw|PUT|통장 출금 API|O|
|/api/account/history/get/history|GET|통장 내역 조회 API|O
|/api/deposit|GET|모든 재일 은행 생성 가능 통장 조회 API|O|
|/api/card/create/card|POST|카드 생성 API|O|
|/api/card/get/mycard/{userId}|GET|자신의 카드 리스트 조회 API|O|
|/api/card/change/{userId}|POST|카드 결제 API|O|
|/api/bank|GET|1KM 이내 은행 지점 조회 API|O|
