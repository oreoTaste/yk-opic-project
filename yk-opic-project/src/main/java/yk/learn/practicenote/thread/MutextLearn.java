// 비동기 방식으로 인한 문제 및 해결책
package yk.learn.practicenote.thread;

public class MutextLearn {

  // 1) Critical Section (Critical Region)
  // 강남, 서초 등 여러 개의 스레드가
  // 같은 객체에 대해 메서드를 호출하여 동시에 값을 변경하려 할 때
  // 서로 그 메모리의 값을 덮어쓰는 문제가 발생한다.
  // 이처럼 여러 스레드가 동시에 실행할 때 문제가 되는 코드들을
  // "임계 구역(Critical Section; Critical Region)"이라 부른다.
  // 이 에제에서는 여러 스레드가 동시에 호출하고,
  // 같은 인스턴스의 변수 값을 변경하는 메서드인 "withdraw()"가
  // critical section이다.
  //
  // 해결책?
  // => 한 번에 한 스레드 만이 변수의 값을 변경하도록 접근을 제한하면 된다.
  // 주의!
  // => 동시에 여러 스레드가 같은 메모리에 대해 값을 조회할 떄는 문제가 발생하지 않는다.
  //
  // 2) Semaphore
  // 세마포어(n); semaphore
  // => 크리티컬 섹션에 진입할 수 있는 스레드의 수를 지정한다.
  // => 자바에서는 세마포어를 지원하지 않는다.
  // => 개발자가 직접 처리해야 한다.
  //
  // 3) Mutex (Mutual Exclusion) = Semaphore (1)
  // 뮤텍스; mutex(mutual exclusion, 상호배제)
  // => 한 번에 오직 한 개의 스레드만이 크리티컬 섹션에 접근할 수 있다.
  // => 예) 선풍기 풍량세기, 라디오 채널, TV 채널 등
  // => semaphore(1)과 같다.
  // => 자바는 synchronized 키워드를 통해 뮤텍스를 사용할 수 있다.
  //
  // 4) Thread-Safe : Critical Section이 없는 즉, Synchronized가 필요 없는.
  
  public static void main(String[] args) throws InterruptedException {
    Account account = new Account("111-11-1111-111", 1_000_000);

    Thread 강남 = new Thread(new ATM("강남", account));
    Thread 서초 = new Thread(new ATM("서초", account));
    Thread 부산 = new Thread(new ATM("부산", account));
    Thread 대전 = new Thread(new ATM("대전", account));
    Thread 광주 = new Thread(new ATM("광주", account));

    강남.start();
    서초.start();
    부산.start();
    대전.start();
    광주.start();
    
  }
}
