# 46 - 객체 생성을 자동화하는 IoC 컨테이너 만들기

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

### 훈련2: 특정 패키지의 파일 시스템 경로를 알아낸다.(ApplicationContext02)

- com.eomcs.util.ApplicationContext 클래스 변경
  - 패키지명을 입력받아서 파일 시스템의 경로를 알아낸다.
- com.eomcs.lms.DataLoaderListener 의 이름 변경
  - 이제 이 클래스는 데이터를 저장하고 로딩하는 역할을 넘어섰다.
  - 애플리케이션을 실행할 때 사용할 객체나 환경을 준비하는 일을 한다.
  - 그래서 이름을 그에 걸맞게 'ContextLoaderListener'라 변경한다.
- com.eomcs.lms.ContextLoaderListener 변경
  - ApplicationContext 객체를 생성하여 맵에 보관한다.
  
### 훈련3: 패키지 폴더의 파일 이름을 알아낸다.(ApplicationContext03)

- com.eomcs.util.ApplicationContext 클래스 변경
  - 패키지 폴더를 뒤져 모든 파일 이름을 가져온다.
  - findFiles()를 추가한다.

### 훈련4: 파일 중에서 클래스 파일의 이름만 추출한다.(ApplicationContext04)

- com.eomcs.util.ApplicationContext 클래스 변경
  - findFiles()를 findClasses()로 변경한다.
  - listFiles()에 FileFilter를 꼽는다.

### 훈련5: 중첩 파일은 제외한다.(ApplicationContext05)

- com.eomcs.util.ApplicationContext 클래스 변경
  - FileFilter에 중첩 파일 제거 조건을 붙인다.
  
### 훈련6: 클래스 이름에 패키지명을 포함한다.(ApplicationContext06)

- com.eomcs.util.ApplicationContext 클래스 변경
  - findClasses()의 두 번째 파라미터에 패키지 이름을 전달한다.
  
### 훈련7: 클래스 이름에서 확장자 .class를 제거한다.(ApplicationContext07)

- com.eomcs.util.ApplicationContext 클래스 변경
  - findClasses()를 변경한다.
  
### 훈련8: 객체를 생성할 수 있는 concrete class 만 추출한다.(ApplicationContext08)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - isConcreteClass()를 추가한다.
    - reflection API를 사용하여 인터페이스와 추상클래스 등을 구분한다.
  - findClasses()를 변경한다.
    - 메서드 선언문에 예외 처리를 추가한다.
    - isConcreteClass()를 통해 concrete class를 구분한다.
  
### 훈련9: concrete class의 타입 정보를 목록에 보관한다.(ApplicationContext09)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - concrete class의 타입 정보를 저장할 필드를 선언한다.
  - findClasses()에 타입 정보를 추가하는 코드를 넣는다.
  - 생성자에서 목록에 등록된 클래스를 출력하여 확인해본다.

### 훈련10: concrete class를 생성하는 메서드 추가한다.(ApplicationContext10)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - createObject()를 추가한다.
  - 생성자 변경: 각 concrete class 에 대해 createObject()를 호출한다.
  
### 훈련11: concrete class의 생성자를 알아낸다.(ApplicationContext11)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - reflection API를 사용하여 클래스의 생성자를 알아낸다.
  - createObject()를 변경한다.
  
### 훈련12: 생성자의 파라미터를 알아낸다.(ApplicationContext12)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - reflection API를 사용하여 생성자의 파라미터를 알아낸다.
  - createObject()를 변경한다.
  
### 훈련13: 파라미터의 값을 준비하는 메서드를 추가한다.(ApplicationContext13)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - createObject()를 변경한다.
  - getParameterValues()를 추가한다.
  
### 훈련14: 생성자를 호출하여 객체풀에 보관한다.(ApplicationContext14)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - 객체풀 역할을 수행할 필드 추가: HashMap<String,Object> objPool
  - createObject()를 변경한다.
  
### 훈련15: 객체 생성 중에 발생한 오류 처리(ApplicationContext15)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - 생성자 변경
  
### 훈련16: 생성자 파라미터 값을 준비하는 메서드를 추가한다.(ApplicationContext16)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - getParameterValues()를 변경한다.
  - getParameterValue()를 추가한다.
  
### 훈련17: 객체풀에서 먼저 파라미터 값을 찾는다.(ApplicationContext17)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - getParameterValue()를 변경한다.
  
### 훈련18: 객체풀에 없으면 concrete class 중에서 찾는다.(ApplicationContext18)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - getParameterValue()를 변경한다.
  - findAvailableClass()를 추가한다.
  - createObject()를 변경한다.
    - 생성한 객체를 호출한 쪽에서 사용할 수 있도록 리턴한다.
  
### 훈련19: 파라미터 타입이 인터페이스일 경우 구현체를 찾는다.(ApplicationContext19)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - findAvailableClass()를 변경한다.
  - concrete class 목록에서 인터페이스를 구현한 클래스가 있는지 조사한다. 
  
### 훈련20: 파라미터 타입의 서브 클래스를 찾는다.(ApplicationContext20)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - findAvailableClass()를 변경한다.
  - isChildClass()를 추가한다.
  
### 훈련21: IoC 컨테이너 외부에서 생성한 객체를 추가한다.(ApplicationContext21)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - 생성자를 변경한다.
  - 파라미터로 받은 맵에서 객체를 꺼내 객체풀에 담는다.
- com.eomcs.lms.ContextLoaderListener 변경
  - Mybatis 관련 객체를 별도의 Map에 보관하여 ApplicationContext 객체를 생성할 때 넘겨준다.
  
### 훈련22: IoC 컨테이너에 보관된 객체를 확인한다.(ApplicationContext22)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - printBeans()를 추가한다.
- com.eomcs.lms.ContextLoaderListener 변경
  - ApplicationContext를 생성한 후 printBeans()를 호출하여 생성된 객체를 확인한다.
  - ServerApp에서 ApplicationContext를 사용할 수 있도록 맵에 담는다.
 
### 훈련23: IoC 컨테이너에 보관된 객체를 꺼내는 메서드를 추가한다.(ApplicationContext23)
  
- com.eomcs.util.ApplicationContext 클래스 변경
  - getBean()을 추가한다.
- com.eomcs.lms.ServerApp 변경
  - ApplicationContext를 사용하여 객체를 관리한다.
   
### 훈련24: 애노테이션을 이용하여 생성된 객체의 이름을 관리한다.(ApplicationContext24)

- com.eomcs.util.Component 애노테이션 추가
  - 빈의 이름을 설정하는 애노테이션을 정의한다.
- com.eomcs.lms.servlet.XxxServlet 변경
  - 클래스에 Component 애노테이션을 적용하여 이름을 지정한다.
- com.eomcs.util.ApplicationContext 클래스 변경
  - getBeanName()을 추가한다.
    - 객체를 객체풀에 저장할 때 Component 애노테이션에서 이름을 가져와서 저장한다.
    - Component 애노테이션이 없으면 그냥 클래스 이름으로 저장한다.
  - createObject()를 변경한다.
    - 객체를 저장할 때 getBeanName()의 리턴 값을 사용한다.
  
### 훈련25: @Component 애노테이션이 붙은 객체만 관리한다.(ApplicationContext)

- com.eomcs.lms.servlet.impl.XxxServiceImpl 변경
  - 클래스에 Component 애노테이션을 적용한다.
- com.eomcs.util.ApplicationContext 클래스 변경
  - isConcreteClass()를 isComponentClass()로 변경한다. 
  - @Component가 붙은 클래스만 찾아내서 객체를 생성한다.
  
### 훈련26: IoC 컨테이너의 이점을 활용해보자.

- com.eomcs.lms.servlet.HelloServlet 추가
  - 클라이언트가 "/hello"를 요청했을 때 "안녕하세요!"하고 인사말을 응답한다.
  - IoC 컨테이너를 도입하면, 새 명령을 처리하는 서블릿이 추가되더라도 
    기존 코드(예: ServerApp)를 변경할 필요가 없다. 
  
### 훈련27: IoC 컨테이너의 이점을 활용해보자. II

- com.eomcs.lms.servlet.HelloServlet 삭제
  - 기능을 제거하고 싶다면 그냥 클래스를 지우면 된다.
  - 기존 코드를 손댈 필요가 없다.
  
  
