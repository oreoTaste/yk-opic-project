# 33 - 동일한 자원으로 더 많은 클라이언트의 요청을 처리하는 방법

## 학습목표

- 'stateful'을 'stateless'통신방식으로 바꿀 수 있다.
- 'stateless'통신방식의 장단점을 이해한다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ServerApp.java 변경


## 실습  

### 훈련 1: 'stateful' 통신방식을 'stateless'통신방식으로 바꿔라

- com.eomcs.lms.ServerApp.java 변경한다.
  - 클라이언트와 연결되면 한번의 요청을 처리 후 즉시 연결을 끊는다.

  