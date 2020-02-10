# 32_8 - DAO클래스의 공통점을 찾아서 수퍼클래스로 정의하기 (generalization)

## 학습목표

- 상속의 기법 중 generalization을 이해한다
- generalization을 구현할 수 있다.

### 상속
- Specialization : 수퍼클래스를 상속받아 기능을 덧붙여 특별한 서브클래스 만들기
- Generalization : 클래스들의 공통점을 뽑아 수퍼클래스로 만든 후 상속맺기

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/AbstractObjectFileDao.java 추가
- src/main/java/com/eomcs/lms/dao/BoardObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/LessonObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/MemberObjectFileDao.java 변경

## 실습  

### 훈련 1: DAO의 공통점을 뽑아 수퍼클래스로 정의하라.

- src/main/java/com/eomcs/lms/dao/AbstractObjectFileDao.java 를 생성하라

### 훈련 2: BoardObjectFileDao가 AbstractObjectFileDao를 상속받도록 변경하라.

- src/main/java/com/eomcs/lms/dao/BoardObjectFile.java 를 변경하라

### 훈련 3: LessonObjectFileDao가 AbstractObjectFileDao를 상속받도록 변경하라.

- src/main/java/com/eomcs/lms/dao/LessonObjectFile.java 를 변경하라

### 훈련 4: MemberObjectFileDao가 AbstractObjectFileDao를 상속받도록 변경하라.

- src/main/java/com/eomcs/lms/dao/MemberObjectFile.java 를 변경하라