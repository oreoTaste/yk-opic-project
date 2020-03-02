package yk.learn.practicenote.thread;

import java.util.ArrayList;
import java.util.Scanner;
import yk.opic.util.Prompt;

/*
 * 1) Critical Section : 여러개의 스레드가 동시에 메서드를 호출할때 발생하는 영역 (메모리 덮어쓰기로 인한 오류)
 *
 * 2) Semaphore : Critical Section에 진입할 수 있는 스레드 개수
 *
 * 3) Mutex (Mutual Exclusive) : 상호배제 (즉, Semaphore(1)과 같은 의미)
 *    하나의 스레드만이 크리티컬 섹션에 접근할 수 있다.
 *
 * 4) Thread-Safe : Critical Section이 없는 (잘짜여진? 코드)
 *
 */


public class ThreadPoolTest {
  static Prompt prompt = new Prompt(new Scanner(System.in));

  public static void main(String[] args) {

    MyThreadPool threadPool = new MyThreadPool();

    while (true) {
      String str = prompt.inputString("카운트? ");
      if (str.equals("quit")) {
        break;
      }

      int count = Integer.parseInt(str);

      // 스레드풀에서 스레드를 한 개 꺼낸다.
      MyThread t = threadPool.get();
      if (t == null) {
        System.out.println("남는 스레드가 없습니다!");
        continue;
      }

      // 스레드의 카운트를 설정한다. 그러면 카운트를 시작할 것이다.
      t.setCount(count);
    }

    System.out.println("main 스레드 종료!");
  }
}

class MyThread extends Thread {
  int count;
  ThreadPool threadPool;

  MyThread(String label, ThreadPool threadPool) {
    super(label);
    this.threadPool = threadPool;
  }

  void setCount(int count) {
    this.count = count;
    synchronized(this) {
      notify();
    }
  }

  @Override
  public void run() {
    while(true) {
      try {

        synchronized(this){
          wait();

          for(int i = count; i > 0; i--) {
            System.out.printf("%s%d\n", getName(), i);
            Thread.sleep(1000);
          }
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      threadPool.add(this);
    }
  }

}


interface ThreadPool {
  Thread get();

  void add(Thread obj);
}

class MyThreadPool implements ThreadPool {
  ArrayList<MyThread> list = new ArrayList<>();

  public MyThreadPool() {
    MyThread t1 = new MyThread("Thread No.1 => ", this);
    t1.start();
    list.add(t1);

    MyThread t2 = new MyThread("Thread No.2 => ", this);
    t2.start();
    list.add(t2);

    MyThread t3 = new MyThread("Thread No.3 => ", this);
    t3.start();
    list.add(t3);
  }

  // 스레드 풀에서 한 개의 스레드를 꺼낸다.
  @Override
  public MyThread get() {
    if (list.size() > 0) {
      return list.remove(0);
    }
    return null;
  }

  // 스레드를 다 쓴 후에는 다시 스레드 풀에 돌려준다.
  @Override
  public void add(Thread t) {
    list.add((MyThread) t);
  }
}
