# 32_11 - 서버에서 제공한 프록시 객체를 사용하여 데이터를 처리하기

## 학습목표

- 프록시 패턴의 이점을 이해한다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/BoardDao.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/XxxDaoProxy.java 추가
- src/main/java/com/eomcs/lms/handler/XxxCommand.java 변경
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습  

### 훈련 1: 서버 프로젝트에서 Dao 프록시 클래스를 가져오라.

- com.eomcs.lms.dao.XXXDao 인터페이스를 가져온다.
- com.eomcs.lms.dao.proxy 패키지와 그 하위 클래스를 모두 가져온다.

### 훈련 2: BoardXXCommand 객체가 BoardDaoProxy 객체를 사용하여 데이터를 처리하게 하라.

- com.eomcs.lms.handler.BoardXXXCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.
- com.eomcs.lms.ClientApp 변경한다.
  - BoardDaoProxy 객체를 생성한다.
  - BoardXXXCommand 객체를 수정한다.

### 훈련 3: LessonXXCommand 객체가 LessonDaoProxy 객체를 사용하여 데이터를 처리하게 하라.

- com.eomcs.lms.handler.LessonXXXCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.
- com.eomcs.lms.ClientApp 변경한다.
  - LessondDaoProxy 객체를 생성한다.
  - LessonXXXCommand 객체를 수정한다.
  
### 훈련 4: MemberXXCommand 객체가 MemberDaoProxy 객체를 사용하여 데이터를 처리하게 하라.

- com.eomcs.lms.handler.MemberXXXCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.
- com.eomcs.lms.ClientApp 변경한다.
  - MemberDaoProxy 객체를 생성한다.
  - MemberXXXCommand 객체를 수정한다.
  