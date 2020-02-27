# 33 - 'stateless'방식으로 변경 및 서버연결부분 리팩토링 하기

## 학습목표

- 'stateful'을 'stateless'통신방식으로 바꿀 수 있다.
- 'stateless'통신방식의 장단점을 이해한다.
- refactoring의 목적 달성

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/CleintApp.java 변경
- src/main/java/com/eomcs/lms/dao/proxy/XXXXProxy.java 변경
- src/main/java/com/eomcs/lms/CleintApp.java 변경
- src/main/java/com/eomcs/lms/dao/proxy/Worker.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/DaoProxyHelper.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/XxxDaoProxy.java 추가
- src/main/java/com/eomcs/lms/ClientApp.java 추가


## 실습  

### 훈련 1: 'stateful' 통신방식을 'stateless'통신방식으로 바꿔라

- com.eomcs.lms.CleintApp.java 변경한다.
  - 한번 연결에 요청/응답을 한번만 수행한다.

### 훈련 2: 프록시 클래스 생성부분을 변경하라.

- com.eomcs.lms.dao.proxy.XXXDaoProxy.java 변경한다.
  - 요청할때 서버에 연결한다.(host주소, port명을 받아서)
  
### 훈련 3: 서버와 통신을 담당할 실제 작업의 규칙을 정의하라.

- com.eomcs.lms.dao.proxy.Worker 인터페이스를 정의한다.


### 훈련 4: DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의하라.

- com.eomcs.lms.dao.proxy.DaoProxyHelper 인터페이스를 정의한다.


### 훈련 5: DaoProxyHelper를 사용하도록 DaoProxy를 변경하라.

- com.eomcs.lms.dao.proxy.XxxDaoProxy를 변경한다.