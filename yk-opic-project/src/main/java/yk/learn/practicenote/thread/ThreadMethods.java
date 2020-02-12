// 스레드 만들기 II - Runnable 인터페이스 구현 + Thread
package yk.learn.practicenote.thread;

public class ThreadMethods {

  public static void main(String[] args) throws InterruptedException {

    class MyThread extends Thread {
      @Override
      public void run() {
        testCode("====>");
      }
    }
    
    class MyRunnable implements Runnable {
      @Override
      public void run() {
        testCode("=>");
      }
    }

    Thread myRunnable = new Thread(new MyRunnable());
    System.out.println("myRunnable waits for 3 sec");
    Thread.sleep(3000);
    myRunnable.start();
    myRunnable.join();
    
    System.out.println();

    MyThread myThread = new MyThread();
    System.out.println("myThread waits for 3 sec");
    Thread.sleep(3000);
    myThread.start();
    myThread.join();

    System.out.println();
    
    System.out.println("mainThread waits for 3 sec");
    Thread.sleep(3000);
    testCode("--------->");
    
  }

  public static void testCode(String string) {
      for (int i = 0; i < 5; i++) {
        System.out.println(string + i);
    }
  }

}
