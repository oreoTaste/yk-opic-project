# 37_2 - Application Server(AS) 구조로 변경
: Sevlet + DAO 적용

## 학습목표

- Application Server(AS) 아키텍처의 구성과 특징을 이해한다.
- 통신 프로토콜 규칙에 따라 동작하는 서버를 만들 수 있다.
- DBMS연동을 위해 JDBC 드라이버를 추가할 수 있다.


## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/mariadb 디렉토리 생성
- src/main/java/com/eomcs/lms/dao/mariadb/BoardDaoImpl.java 추가
- src/main/java/com/eomcs/lms/dao/mariadb/LessonDaoImpl.java 추가
- src/main/java/com/eomcs/lms/dao/mariadb/MemberDaoImpl.java 추가
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/servlet/BoardListServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/LessonListServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/MemberListServlet.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련1: MariaDB JDBC Driver를 프로젝트에 추가하라

- build.gradle 변경
  - mvnrepository.com 또는 search.mvn.com에서 'mariadb jdbc' 검색!
  - library 정보를 dependencies{} 블록에 추가한다.
  - 'gradle cleanEclipse' 명령을 통해 기존 이클립스 설정을 제거한다.
  - 'gradle eclipse'명령을 통해 이클립스 설정파일을 생성한다.
  - 이클립스 IDE에서 프로젝트를 refresh한다
  
- 프로젝트에 추가되었는지 확인한다 (라이브러리 목록을 확인한다)

### 훈련2: 클라이언트 프로젝트에서 만든 DAO관련 클래스를 가져오라

- com.eomcs.lms.dao.mariadb 패키지 생성
- com.eomcs.lms.dao.mariadb.BoardDaoImpl : LessonDaoImp : MemberDaoImp 복사해오기

### 훈련3: Connection 객체를 준비해서 DAO를 준비할 때 주입하라

- com.eomcs.lms.DataLoaderListener 변경
  - Connection 객체 생성
  - mariadb 관련 DAO 객체 생성
  
### 훈련4: 통신규칙1에 따라 동작하도록 Servlet을 변경하라.

- com.eomcs.lms.servlet.Servlet 변경
  - service() 파라미터의 타입을 PrintStream, Scanner로 추가한다
    (default로 선언하면 기존 구현체가 영향을 받지 않음)
- com.eomcs.lms.servlet.BoardListServlet 변경
  - 새규칙에 따른 메서드 구현으로 변경한다.
- com.eomcs.lms.servlet.MemberListServlet 변경
  - 새규칙에 따른 메서드 구현으로 변경한다.

### 훈련5: 클라이언트의 '/board/list' 요청을 BoardListServlet으로 처리하라.

- com.eomcs.lms.ServerApp 변경
  - 클라이언트 명령을 처리할 서블릿을 찾아 실행한다.

### 훈련6: 클라이언트의 '/member/list' 요청을 MemberListServlet으로 처리하라.

- com.eomcs.lms.ServerApp 변경
  - 클라이언트 명령을 처리할 서블릿을 찾아 실행한다.
  
  ### 훈련5: 클라이언트의 '/board/list' 요청을 MemberListServlet으로 처리하라.

- com.eomcs.lms.ServerApp 변경
  - 클라이언트 명령을 처리할 서블릿을 찾아 실행한다. 
  
### 훈련7: 클라이언트의 '/lesson/list' 요청을 LessonListServlet으로 처리하라.

- com.eomcs.lms.servlet.LessonListServlet 변경
  - 기존 service() 메서드를 service(Scanner in, PrintStream out)으로 변경한다.
  - '통신 규칙1'에 따라 응답하도록 변경한다.
- com.eomcs.lms.ServerApp 변경
  - '/lesson/list' 요청을 처리할 LessonListServlet을 서블릿맵에 등록한다.
  
  