# 40_1 - Connection을 스레드에 보관하기 : ThreadLocal 사용하기


## 학습목표
- ThreadLocal의 동작원리를 이해한다.
- ThreadLocal을 사용하여 각 스레드에 값을 저장할 수 있다.


# Connection을 Thread에 보관하는 이유?
- 스레드가 실행하는 데이터 변경작업을 한단위로 묶기 위함이다.


## 실습 소스 및 결과
- src/main/java/com/eomcs/dao/mariadb/xxxDaoImpl.java 변경
- src/main/java/com/eomcs/DataLoaderListener.java 변경
- src/main/java/com/eomcs/util/ConnectionFactory.java 추가

## 실습  

### 훈련1: 커넥션 팩토리에서 생성한 Connection 객체를 스레드에 보관하라.

- com.eomcs.util.ConnectionFactory.java 변경
  - 스레드에 보관된 객체가 없다면, 새로 생성하여 리턴한다. (사용후 보관)
  - 스레드에 보관된 객체가 있다면, 그 객체를 꺼내 리턴한다.

## 문제점
- 현재 스레드풀(ExecutorService)를 이용하여 스레드를 관리하고 있다.
- 스레드를 사용한 후 버리지 않고, 풀에 보관했다가 
  다음에 클라이언트 요청이 들어오면 재사용한다.

- DAO가 사용하는 Connection 객체는 스레드에 보관하지만,
  DAO의 메서드에서 Connection을 사용한 후에 닫는다.

- 따라서 닫힌 Connection을 재사용하게 되면 오류가 발생한다.


## 해결책
- 클라이언트에게 응답을 완료한 후에 스레드에 보관된 Connection 객체를 제거한다.

### 훈련2: 클라이언트에 응답한 후 스레드에 보관된 Connection 객체를 제거하라.

- com.eomcs.util.ConnnectionFactory 변경
  - Thread에 보관된 Connection 객체를 제거하는 메서드 추가한다.
  - removeConnection();
- com.eomcs.lms.DataLoaderListener 변경
  - ServerApp에서 ConnectionFactory를 사용할 수 있도록 맵에 보관한다.
- com.eomcs.lms.ServerApp 변경
  - 클라이언트 요청을 처리한 후에 ConnectionFactory를 통해 Thread에서 Connection을 제거한다.
