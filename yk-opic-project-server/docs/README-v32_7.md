# 32_7 - 데이터 처리 코드를 별도의 클래스(DAO)로 정의하여 객체화 시키기

## 학습목표

- DAO(Data Access Object)의 역할과 이점을 이해한다.
- 데이터 처리 코드를 DAO로 분리할 수 있다.

### DAO ? (Data Access Object) 데이터 처리 역할을 수행하는 객체
- 데이터 처리방식을 캡슐화(=추상화 = 클래스로 정의) 하여, 객체의 사용법을 일관성 있게 만든다.
- 즉, 데이터 처리방식(배열, 스택, 큐, 맵, 파일, 데이터베이스 등)을 클래스로 포장(캡슐화)하면 
  데이터 처리방식에 상관없이 메서드 사용을 통일 할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련 1: 게시물 데이터를 처리하는 DAO 클래스를 정의하라

- com.eomcs.lms.dao 패키지 생성
- com.eomcs.lms.dao.BoardFileDao 클래스 정의

### 훈련 2: BoardFileDao 객체를 적용하라

- com.eomcs.lms.DataLoaderListener를 변경한다.
  - 게시물 데이터를 로딩, 저장하는 기존 코드를 제거한다.
  - 대신 BoardFileDao 객체를 생성한다.
  
- com.eomcs.lms.ServerApp을 변경한다.
  - Map에서 BoardFileDao를 꺼내 관련 커맨드 객체에 주입한다.
  
- BoardXXXServlet을 변경한다.
  - 생성자에서 List객체를 받는 대신에 BoardFileDao 객체를 받는다.
  - 데이터를 저장, 조회, 변경, 삭제할때 BoardFileDao 객체를 통해 처리한다.

### 훈련 3: MemberFileDao 객체를 적용하라

- com.eomcs.lms.DataLoaderListener를 변경한다.
  - 게시물 데이터를 로딩, 저장하는 기존 코드를 제거한다.
  - 대신 MemberFileDao 객체를 생성한다.
  
- com.eomcs.lms.ServerApp을 변경한다.
  - Map에서 MemberFileDao를 꺼내 관련 커맨드 객체에 주입한다.
  
- MemberXXXServlet을 변경한다.
  - 생성자에서 List객체를 받는 대신에 MemberFileDao 객체를 받는다.
  - 데이터를 저장, 조회, 변경, 삭제할때 MemberFileDao 객체를 통해 처리한다.
  
### 훈련 4: LessonFileDao 객체를 적용하라

- com.eomcs.lms.DataLoaderListener를 변경한다.
  - 게시물 데이터를 로딩, 저장하는 기존 코드를 제거한다.
  - 대신 LessonFileDao 객체를 생성한다.
  
- com.eomcs.lms.ServerApp을 변경한다.
  - Map에서 LessonFileDao를 꺼내 관련 커맨드 객체에 주입한다.
  
- LessonXXXServlet을 변경한다.
  - 생성자에서 List객체를 받는 대신에 LessonFileDao 객체를 받는다.
  - 데이터를 저장, 조회, 변경, 삭제할때 LessonFileDao 객체를 통해 처리한다.
