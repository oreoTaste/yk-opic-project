# 41_1 - Connection Pooling 도입하기

## 학습목표

- Pooling기법의 원리와 특징을 이해한다.
- Connection Pool을 구현할 수 있다.

# Pooling 기법
- 생성된 객체를 재사용(!) 하는 것.
- 객체를 사용한 후에 버리지 않고, 보관하는 방법으로 진행.
  => 객체 생성에 소요되는 시간을 줄일 수 있다.
  => 예시) DB Connection Pool, Thread Pool 등
- GoF의 Flyweight 디자인패턴에 해당된다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/sql/ConnectionFactory.java 삭제
- src/main/java/com/eomcs/sql/DataSource.java 추가
- src/main/java/com/eomcs/sql/PlatformTransactionManager.java 변경
- src/main/java/com/eomcs/lms/dao/mariadb/XxxDaoImpl.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습

### 훈련1: DB Connection Pool 객체를 생성하라.

- com.eomcs.sql.DataSource 추가
  - 트랜잭션을 시작시키고, 커밋/롤백하는 메서드를 정의한다.
  
  
### 훈련2: PlatformTransactionManager을 변경하라.

- com.eomcs.sql.PlatformTransactionManager 변경
  - ConnectionFactory 대신 DataSource를 사용하도록 한다.
  
### 훈련3: DAO가 DataSource를 사용하도록 변경하라

- com.eomcs.lms.dao.mariadb.xxxDaoImpl 변경
  - ConnectionFactory 대신 DataSource를 사용하도록 변경한다.

### 훈련4: DataSource를 Dao에 주입하라

- com.eomcs.lms.DataLoaderListener 변경
  
### 훈련5: 클라이언트 요청을 처리한후 Connection을 닫지말고 반납하라.

- com.eomcs.lms.ServerApp 변경
  - 클라이언트에게 응답한 후, 스레드에서 커넥션 객체를 제거한다.
  - 제거된 커넥션 객체를 재사용하기 위해 닫지 않는다.  