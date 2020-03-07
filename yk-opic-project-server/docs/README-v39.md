# 39 - Connection 개별화하기: 커넥션 객체 생성에 Factory Method 패턴 적용하기


## 학습목표

- 트랜잭션 사용하기 전의 문제점을 이해한다.
- Factory Method 디자인 패턴의 원리와 사용 목적을 이해한다.
- Factory Method 설계 기법에 따라 구현할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/dao/mariadb/xxxDaoImpl.java 변경
- src/main/java/com/eomcs/DataLoaderListener.java 변경
- src/main/java/com/eomcs/util/ConnectionFactory.java 추가

## 실습  

### 훈련1: 메서드를 호출할 때마다 DBMS와 별도로 연결하라.

- com.eomcs.lms.dao.mariadb.xxxDaoImpl 변경
  - 생성자에서 파라미터로 Connection 객체를 받는대신에 DB연결정보를 받는다.
  - 각 메서드에서 JDBC URL과 username, password를 사용하여 DBMS에 연결한다.

- com.eomcs.lms.DataLoaderListener 변경
  - Connection 객체를 생성하지 않으며, 
  - 대신 DBMS 연결정보를 준비하여 DAO 객체 구현시 넘겨준다.
  
  
### 훈련2: Factory Method 구현하기
  
- com.eomcs.util.ConnectionFactory 추가
  - Connection 객체를 생성하는 메서드 추가한다.

- com.eomcs.lms.DataLoaderListener 변경
  - DAO 구현체에 ConnectionFactory를 주입한다.
  
- com.eomcs.lms.dao.mariadb.xxxDaoImpl 변경
  - 직접 Connection 객체를 생성하는 대신 ConnectionFactory 객체를 통해 Connection을 얻는다.
  
  
### 메서드 마다 커넥션을 구분하는 방식의 문제점

- 메서드 마다 별도의 커넥션을 사용한다.
- 따라서 PhotoBoardDao의 insert()와 PhotoFileDao의 insert()를 
  한 단위 작업으로 묶을 수 없다.
- 즉 사진 게시글 입력과 첨부 파일 입력을 한 단위의 작업으로 다룰 수 없다.
- 트랜잭션을 구현할 수 없다. 