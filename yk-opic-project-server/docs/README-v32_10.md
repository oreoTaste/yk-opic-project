# 32_10 - 인터페이스를 이용하여 DAO 호출 규칙을 통일하기

## 학습목표

- 인터페이스 문법의 존재이유를 이해한다.
- 인터페이스를 정의하고 구현할 수 있다.

## 인터페이스
- 사용자(서블릿) 가 피사용자(DAO) 사이에 호출규칙을 정의하는 문법이다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/BoardDao.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/BoardObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/BoardJsonFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/LessonDao.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/LessondObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/LessonJsonFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/MemberDao.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/MemberObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/MemberJsonFileDao.java 변경

- src/main/java/com/eomcs/lms/servlet/BoardXXXServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/LessonXXXServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/MemberXXXServlet.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련 1: BoardXXXServlet이 사용할 DAO 호출규칙을 정의하고 구현하기

- com.eomcs.lms.dao.BoardDao 인터페이스를 생성한다.
- com.eomcs.lms.dao.BoardObjectFileDao 클래스를 생성한다.
- com.eomcs.lms.dao.json.BoardObjectFileDao 클래스를 생성한다.
- com.eomcs.lms.DataLoaderListener 변경한다.


### 훈련 2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- com.eomcs.lms.dao.BoardObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 훈련 3: LessonObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- com.eomcs.lms.dao.LessonObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 훈련 4: MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- com.eomcs.lms.dao.MemberObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 훈련 5: XxxObjectFileDao 대신 XxxJsonFileDao를 사용하도록 서블릿 클래스를 변경하라.

- com.eomcs.lms.servlet.BoardXxxServlet 변경한다.
- com.eomcs.lms.servlet.LessonXxxServlet 변경한다.
- com.eomcs.lms.servlet.MemberXxxServlet 변경한다.

### 훈련 6: 애플리케이션이 시작할 때 XxxObjectFileDao 대신 XxxJsonFileDao를 준비하라.

- com.eomcs.lms.DataLoaderListener 변경한다.

### 훈련 7: XxxObjectFileDao 대신 XxxJsonFileDao를 서블릿 객체에 주입하라.

- com.eocms.lms.ServerApp 변경한다.
 



  
  