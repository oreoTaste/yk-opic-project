# 33_3 - Refactoring : 서버 연결부분을 캡슐화하기

## 학습목표

- refactoring의 목적 달성

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/proxy/Worker.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/DaoProxyHelper.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/XxxDaoProxy.java 추가
- src/main/java/com/eomcs/lms/ClientApp.java 추가

## 실습  

### 훈련 1: 서버와 통신을 담당할 실제 작업의 규칙을 정의하라.

- com.eomcs.lms.dao.proxy.Worker 인터페이스를 정의한다.


### 훈련 2: DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의하라.

- com.eomcs.lms.dao.proxy.DaoProxyHelper 인터페이스를 정의한다.


### 훈련 3: DaoProxyHelper를 사용하도록 DaoProxy를 변경하라.

- com.eomcs.lms.dao.proxy.XxxDaoProxy를 변경한다.

