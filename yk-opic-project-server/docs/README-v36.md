# 36 - 데이터 관리를 전문 프로그램인 DBMS에게 맡기기

## 학습목표
- 오픈 소스 DBMS 'MariaDB'를 설치할 수 있다.
- DBMS에 사용자와 데이터베이스를 추가할 수 있다.
- 테이블 생성과 예제 데이터를입력할 수 있다.
- JDBC API의 목적을 이해한다.
  - DBMS와의 통신을 담당하는 proxy객체의 사용규칙을 통일하는것.
- JDBC Driver의 의미를 이해한다.
  - JDBC API규칙에 따라 정의한 클래스들(라이브러리)
- JDBC API를 활용하여 DBMS에 데이터를 입력, 조회, 변경, 삭제할 수 있다.
- JDBC 프로그램 코드를 클래스로 캡슐화 할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련 1: MariaDB에 애플리케이션에서 사용할 사용자와 데이터베이스를 추가한다.

- MariaDB에 연결하기
 > $ mysql -u root -p;
 
- 사용자 생성하기
 > $ CREATE USER 'study'@'localhost' IDENTIFIED BY '1111';
 
- 데이터베이스 생성하기
 > $ CREATE DATABASE studydb;
 
- 사용자에게 DB 사용권한을 부여하기
 > $ GRANT ALL ON studydb.* TO 'study'@'localhost'

### 훈련 2: 애플리케이션에 사용할 테이블과 예제데이터를 사용한다.
 
  
  
  
  
  
 
