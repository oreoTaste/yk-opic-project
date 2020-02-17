# 33_3 - 다중 클라이언트 요청을 동시에 처리하기 (Thread)

## 학습목표

- Thread의 사용목적을 이해한다.
- Thread의 동작원리와 정의하는 방법

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ClientApp.java 추가

## 실습  

### 훈련 1: 클라이언트가 연결되면 스레드를 이용하여 서버를 분기하라.

- com.eomcs.lms.dao.proxy.Worker 인터페이스를 정의한다.


### 훈련 2: DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의하라.

- com.eomcs.lms.dao.proxy.DaoProxyHelper 인터페이스를 정의한다.


### 훈련 3: DaoProxyHelper를 사용하도록 DaoProxy를 변경하라.

- com.eomcs.lms.dao.proxy.XxxDaoProxy를 변경한다.

