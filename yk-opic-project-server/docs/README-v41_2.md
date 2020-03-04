# 41_2 - 트랜젝션 관리자를 사용하는 코드를 캡슐화 하기

## 학습목표

- 트랜젝션 관리자를 사용하는 코드를 캡슐화할 수 있다.
- 반복되는 코드를 캡슐화하여, 코드를 단순화 할 수 있다.
- 인터페이스 사용 목적과 활용법을 이해한다.
- 익명클래스를 정의할 수 있다.
- lambda 문법을 활용할 수 있다.


## 실습 소스 및 결과

- src/main/java/com/eomcs/sql/TransactionCallback.java 추가
- src/main/java/com/eomcs/sql/TransactionTemplate.java 추가
- src/main/java/com/eomcs/lms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/PhotoBoardDeleteServlet.java 변경

## 실습

### 훈련1: 트랜젝션 관리자를 사용하는 코드를 캡슐화하여 별도의 클래스로 분리하라.

- com.eomcs.sql.TransactionTemplate 추가
  - 트랜젝션 관리자를 사용하는 코드를 메서드로 정의한다.
- com.eomcs.sql.TransactionCallback 인터페이스 추가
  - TransactionTemplate을 실행할때 수행할 작업규칙이다.
  
  
### 훈련2: 트랜젝션을 사용할 곳에 TransactionTemplate를 적용하라.

- com.eomcs.lms.servlet.PhotoBoardAddServlet 변경
  - PlatformTransactionManager를 직접사용하는 대신에 TransactionTemplate를 사용한다.
- com.eomcs.lms.servlet.PhotoBoardUpdateServlet 변경
  - PlatformTransactionManager를 직접사용하는 대신에 TransactionTemplate를 사용한다.
- com.eomcs.lms.servlet.PhotoBoardDeleteServlet 변경
  - PlatformTransactionManager를 직접사용하는 대신에 TransactionTemplate를 사용한다.
  
