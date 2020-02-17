# 33_2 - Refactoring : 요청할때마다 프록시와 커맨드 생성하지 않도록 개선하기

## 학습목표

- refactoring의 목적 달성

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/proxy/XXXXProxy.java 변경
- src/main/java/com/eomcs/lms/CleintApp.java 변경


## 실습  

### 훈련 1: 프록시 클래스 생성부분을 변경하라.

- com.eomcs.lms.dao.proxy.XXXDaoProxy.java 변경한다.
  - 요청할때 서버에 연결한다.

  