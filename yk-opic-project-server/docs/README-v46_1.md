# 46_1 - 객체 생성을 자동화하는 IoC 컨테이너 만들기

새 명령을 추가할 때마다 그 명령을 처리할 서블릿 객체를 생성하고 등록해야한다.
또한, 데이터를 다루는 DAO와 비즈니스 로직 및 트랜젝션을 관리하는 Service 객체를 
생성하고 등록해야한다.
IoC 컨테이너를 도입하여 이런 번거로움을 없애보자.
-> 객체 생성 및 등록을 자동화하는 객체를 IoC 컨테이너라고 한다.

## 학습목표

- IoC 컨테이너의 개념과 구동 원리를 이해한다.
- 리플랙션 API를 활용하여 클래스를 정보를 다루고 객체를 생성할 수 있다.

## IoC (Inversion Of Control)
- '제어의 역전'이라 부른다.

1) IoC 예1 - 의존객체 생성
- 보통의 실행흐름은 객체를 사용하는 쪽에서 해당 객체를 만든다.
- 기존 : 쌀을 먹을 사람이 직접 쌀을 기른다.
- 변화 : 쌀이 필요하면 외부에서 쌀을 받는다.
-> but, 이는 실행 흐름을 역행하는 것이다.

2) IoC 예2 - 메서드 호출
- 보통 메서드를 만들면 실행 흐름에 따라 호출한다.
- 그런데 실행계획에 따라서 뿐만 아니라, 특정상태일때 호출하는 경우도 있다.
  (이벤트 핸들러 == 이벤드 리스너 == callback 메서드)

### IoC 컨테이너
- 객체 생성을 전담하는 역할자를 통해 객체가 준비된다 : 빈 컨테이너 (Bean Container)
- 여기에 객체가 사용할 의존객체를 자동으로 주입하는 역할을 추가한다.
- 즉, IoC 컨테이너 = 빈 컨테이너 + 의존객체 주입
- 대표 제품 : Spring IoC 컨테이너


## 실습 소스 및 결과

- src/main/java/com/eomcs/util/Component.java 추가
- src/main/java/com/eomcs/util/ApplicationContext.java 추가
- src/main/java/com/eomcs/lms/service/impl/BoardServiceImpl2.java 삭제
- src/main/java/com/eomcs/lms/service/impl/XxxServiceImpl.java 변경
- src/main/java/com/eomcs/lms/servlet/XxxServlet.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 의 이름 변경
  - ContextLoaderListener.java 로 이름 변경

## 실습  

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/service/impl/BoardServiceImpl2.java 추가
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경

## 실습  

### 훈련1: IoC 컨테이너 클래스를 준비한다.(ApplicationContext01)

- com.eomcs.util.ApplicationContext 클래스 생성

### 훈련2: 특정 패키지에 소속된 클래스 이름을 수집한다.(ApplicationContext02)

- com.eomcs.util.ApplicationContext 클래스 변경
  - 패키지명을 입력받아서 해당 패키지를 뒤져 모든 클래스의 이름을 가져온다.
- com.eomcs.lms.DataLoaderListener 의 이름 변경
  - 이제 이 클래스는 데이터를 저장하고 로딩하는 역할을 넘어섰다.
  - 애플리케이션을 실행할 때 사용할 객체나 환경을 준비하는 일을 한다.
  - 그래서 이름을 그에 걸맞게 'ContextLoaderListener'라 변경한다.
- com.eomcs.lms.ContextLoaderListener 변경
  - ApplicationContext 객체를 생성하여 맵에 보관한다.
  
### 훈련3: 객체를 생성할 수 있는 concrete class 만 추출한다.(ApplicationContext03)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - reflection API를 사용하여 인터페이스와 추상클래스 등을 구분한다.
  
### 훈련4: concrete class의 생성자 정보를 알아낸다.(ApplicationContext04)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - reflection API를 사용하여 클래스의 생성자를 알아낸다.
  - 생성자의 파라미터 정보를 알아낸다.
  
### 훈련5: concrete class의 생성자를 호출하여 객체를 준비한다.(ApplicationContext05)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - concrete class만 따로 로딩하여 목록을 관리한다.
  - reflection API를 사용하여 생성자의 파라미터 정보를 알아낸다.
  - 파라미터 객체를 준비하여 생성자를 호출한다.
  - 생성된 객체를 객체 보관소(objPool)에 저장한다.

### 훈련6: 애노테이션을 이용하여 생성된 객체의 이름을 관리한다.(ApplicationContext06)

- com.eomcs.util.Component 애노테이션 추가
  - 빈의 이름을 설정하는 애노테이션을 정의한다.
- com.eomcs.lms.servlet.XxxServlet 변경
  - 클래스에 Component 애노테이션을 적용하여 이름을 지정한다.
- com.eomcs.util.ApplicationContext 클래스 변경
  - 객체를 객체풀에 저장할 때 Component 애노테이션에서 이름을 가져와서 저장한다.
  - Component 애노테이션이 없으면 그냥 클래스 이름으로 저장한다.
  - 외부에서 생성한 객체를 저장할 수 있도록 생성자 변경한다.
  - 외부에서 저장된 객체를 꺼낼 수 있도록 getBean() 메서드 추가한다.
- com.eomcs.util.ApplicationContext 클래스 변경
  - 외부에서 생성한 객체를 등록한 addBean() 메서드를 추가한다.
  - 내부에서 생성한 객체를 꺼낼 수 있도록 getBean() 메서드를 추가한다.
- com.eomcs.lms.ServerApp 변경
  - ApplicationContext를 사용하여 객체를 관리한다.
  
### 훈련7: @Component 애노테이션이 붙은 객체만 관리한다.(ApplicationContext)

- com.eomcs.lms.servlet.impl.XxxServiceImpl 변경
  - 클래스에 Component 애노테이션을 적용한다.
- com.eomcs.util.ApplicationContext 클래스 변경
  - @Component가 붙은 클래스만 찾아내 객체를 생성한다.
  - 내부에 보관된 객체 정보를 출력하는 printBeans() 추가한다. 
- com.eomcs.ContextLoaderListener 변경
  - ApplcationContext를 생성한 후 printBeans() 호출하여 보관된 객체 정보를 조회한다.
  
### 훈련8: IoC 컨테이너의 이점을 활용해보자.

- com.eomcs.lms.servlet.HelloServlet 추가
  - 클라이언트가 "/hello"를 요청했을 때 "안녕하세요!"하고 인사말을 응답한다.
  - IoC 컨테이너를 도입하면, 새 명령을 처리하는 서블릿이 추가되더라도 
    기존 코드(예: ServerApp)를 변경할 필요가 없다. 
  
### 훈련9: IoC 컨테이너의 이점을 활용해보자. II

- com.eomcs.lms.servlet.HelloServlet 삭제
  - 기능을 제거하고 싶다면 그냥 클래스를 지우면 된다.
  - 기존 코드를 손댈 필요가 없다.
  
  
  