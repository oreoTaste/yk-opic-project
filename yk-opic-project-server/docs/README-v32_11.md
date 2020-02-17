# 32_11 - 프록시 패턴을 적용하여 서버 요청을 간결하게 만들기

## 학습목표

- 프록시 패턴의 구조와 실행 원리를 이해한다
- 프록시 패턴의 사용 목적을 이해한다.
- 프록시 패턴을 적용할 수 있고 사용할 수 있다.

## 프록시
- 실제 일을 하는 객체와 동일하게 인터페이스를 구현한다.
- 프록시 객체의 역할은 실제 작업 객체를 간결하게 사용하는 것이다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/proxy 패키지 생성
- src/main/java/com/eomcs/lms/dao/proxy/BoardDaoProxy.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/proxy/LessonDaoProxy.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/proxy/MemberDaoProxy.java 인터페이스 추가


## 실습  

### 훈련 1: BoardDao의 사용법을 캡슐화 하라

- com.eomcs.lms.dao.proxy 패키지를 생성한다.
- com.eomcs.lms.dao.proxy.BoardDaoProxy 클래스를 생성한다.

### 훈련 2: LessonDao의 사용법을 캡슐화 하라

- com.eomcs.lms.dao.proxy 패키지를 생성한다.
- com.eomcs.lms.dao.proxy.LessonDaoProxy 클래스를 생성한다.

### 훈련 3: MemberDao의 사용법을 캡슐화 하라

- com.eomcs.lms.dao.proxy 패키지를 생성한다.
- com.eomcs.lms.dao.proxy.MemberDaoProxy 클래스를 생성한다.

### 훈련 4: 프록시 객체를 Client 프로젝트에 가져간후 실행을 테스트하라

