# 32_6 - 커맨트 패턴을 적용하여 요청 처리 메서드를 객체화 하기

## 학습목표

- 커맨트 패턴의 동작원리를 이해한다.
- 커맨드 패턴을 코드에 적용할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련 1: 커맨드 패턴의 인터페이스 정의

- com.eomcs.lms.servlet 패키지 생성
- com.eomcs.lms.servlet.Servlet 인터페이스 정의

### 훈련 2: 요청 처리메서드를 인터페이스 규칙에 따라 클래스로 정의하라

- listBoard()를 BoardListServlet 클래스로 정의한다.
- addBoard()를 BoardAddServlet 클래스로 정의한다.
- detailBoard()를 BoardDetailServlet 클래스로 정의한다.
- updateBoard()를 BoardUpdateServlet 클래스로 정의한다.
- deleteBoard()를 BoardDeleteServlet 클래스로 정의한다.



  - if ~ else 분기문에 작성한 코드를 별도의 메서드로 분리하라.
  - listBoard() : 게시물 목록 데이터 요청 처리
  - addBoard() : 게시물 데이터 등록 요청 처리
  - detailBoard() : 게시물 조회 요청 처리
  - updateBoard() : 게시물 변경 요청 처리
  - deleteBoard() : 게시물 삭제 요청 처리
  
  - listMember() : 멤버 목록 데이터 요청 처리
  - addMember() : 멤버 데이터 등록 요청 처리
  - detailMember() : 멤버 조회 요청 처리
  - updateMember() : 멤버 변경 요청 처리
  - deleteMember() : 멤버 삭제 요청 처리
  
  - listLesson() : 수업 목록 데이터 요청 처리
  - addLesson() : 수업 데이터 등록 요청 처리
  - detailLesson() : 수업 조회 요청 처리
  - updateLesson() : 수업 변경 요청 처리
  - deleteLesson() : 수업 삭제 요청 처리
  
