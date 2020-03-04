# 37_1 - Application Server(AS) 구조로 변경
: 규칙1에 따라 통신하는 서버 만들기
: Sevlet + DAO 적용

## 학습목표

- Application Server(AS) 아키텍처의 구성과 특징을 이해한다.
- 통신 프로토콜 규칙에 따라 동작하는 서버를 만들 수 있다.
- DBMS연동을 위해 JDBC 드라이버를 추가할 수 있다.
- 새 기능을 추가하더라도 client App에 재배포할 필요가 없음을 이해한다.
- 멀티 스레드 환경에서 스레드풀의 동작을 제어할 수 있다.

## 실습 소스 및 결과
- src/main/java/com/eomcs/lms/dao/mariadb 디렉토리 생성
- src/main/java/com/eomcs/lms/dao/MemberDao.java 변경
- src/main/java/com/eomcs/lms/dao/mariadb/BoardDaoImpl.java 추가
- src/main/java/com/eomcs/lms/dao/mariadb/LessonDaoImpl.java 추가
- src/main/java/com/eomcs/lms/dao/mariadb/MemberDaoImpl.java 추가
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/servlet/BoardListServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/LessonListServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/MemberListServlet.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경
- src/main/java/com/eomcs/lms/servlet/MemberSearchServlet.java 변경

## 실습  

### 훈련1: 클라이언트와 서버 사이의 기본 통신 규칙을 정한다

- 요청과 응답은 'stateless'방식으로 처리한다.
- 클라이언트가 요청할 때 서버와 연결하고, 서버에서 응답하면 연결을 끊는다.


규칙1) 단순한 명령어 전송과 실행 결과 수신
```
<클라이언트>                                <서버>
서버에 연결 요청        -------------->           연결 승인
명령(CRLF)     -------------->           명령처리
화면 출력                  <--------------           응답(CRLF)
화면 출력                  <--------------           응답(CRLF)
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 훈련2: 통신규칙1에 따라 클라이언트의 요청에 응답하라

- com.eomcs.lms.ServerApp 변경
  - 클라이언트 요청에 대해 간단한 인사말을 출력하도록 변경한다.


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
  
  
### 훈련1: 서버가 클라이언트에게 추가 데이터 입력을 요구할 수 있도록 통신규칙을 변경하라.

규칙1) 사용자입력을 포함하는 경우
```
<클라이언트>                              <서버>
서버에 연결 요청        -------------->           연결 승인
명령(CRLF)     -------------->           명령처리
화면 출력                  <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)    -------------->           입력값 처리
화면 출력                  <--------------           응답(CRLF)
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 훈련2: 통신규칙2에 따라 게시물 번호를 입력받을 수 있도록 BoardDetailServlet을 변경하라.

- com.eomcs.lms.servlet.BoardDetailServlet 변경
  - Servlet 인터페이스에 추가한 Service(Scanner in, PrintStream out) 구현하기
  - 통신규칙2에 따라 클라이언트에게 상세조회할 게시물 번호를 요구하기.
  - 통신규칙1에 다라 응답한다.
- com.eomcs.lms.ServerApp 변경

### 훈련3: 통신규칙2에 따라 게시물을 추가받을 수 있도록 BoardAddServlet을 변경하라.

- com.eomcs.lms.servlet.BoardAddServlet 변경
  - Servlet 인터페이스에 추가한 Service(Scanner in, PrintStream out) 구현하기
  - 통신규칙2에 따라 클라이언트에게 상세조회할 게시물 번호를 요구하기.
  - 통신규칙1에 다라 응답한다.
- com.eomcs.lms.ServerApp 변경
  
  
### 훈련4: 통신규칙2에 따라 게시물을 변경 받을 수 있도록 BoardUpdateServlet을 변경하라.

- com.eomcs.lms.servlet.BoardUpdateServlet 변경
  - Servlet 인터페이스에 추가한 Service(Scanner in, PrintStream out) 구현하기
  - 통신규칙2에 따라 클라이언트에게 상세조회할 게시물 번호를 요구하기.
  - 통신규칙1에 다라 응답한다.
- com.eomcs.lms.ServerApp 변경
  
  
### 훈련4: 통신규칙2에 따라 게시물을 삭제할 수 있도록 BoardDeleteServlet을 변경하라.

- com.eomcs.lms.servlet.BoardDeleteServlet 변경
  - Servlet 인터페이스에 추가한 Service(Scanner in, PrintStream out) 구현하기
  - 통신규칙2에 따라 클라이언트에게 상세조회할 게시물 번호를 요구하기.
  - 통신규칙1에 다라 응답한다.
- com.eomcs.lms.ServerApp 변경

### 훈련5++: 위와 같이 LessonDetailServlet, LessonAddServlet, LessonUpdateServlet, LessonDeleteServlet을 변경하라.



### 훈련1: 회원 검색 기능을 추가하라.

서버에서 애플리케이션을 실행하는 방식의 이점은 새 기능을 추가하더라도
사용자 pc에 클라이언트 프로그램을 재설치할 필요가 없다는 것이다.
검색기능을 추가한 후 이를 확인한다.

- com.eomcs.lms.dao.MemberDao 변경
  -findByKeyword()메서드 추가
  
- com.eomcs.lms.dao.mariadb.MemberDaoImpl 변경
  -findByKeyword()메서드 구현
  
-com.eomcs.lms.servlet.MemberSearchServlet 추가
  - 클라이언트에게 검색 키워드를 요청한다.
  - 클라이언트가 보낸 키워드를 가지고 회원을 검색한다.  
  - 검색한 결과를 가지고 출력 내용을 생성한다.
  - 클라이언트에게 보낸다.

-com.eomcs.lms.ServerApp 변경
  - '/member/search' 명령을 처리할 MemberSearchServlet 객체를 등록한다.

  
### 훈련1: 클라이언트의 '/server/stop' 요청을 처리하라.

- com.eomcs.lms.ServerApp 변경
  - '/server/stop' 명령을 처리한다.
  