// JVM의 전체 스레드 계층도
package yk.learn.practicenote.thread;

public class ThreadHierarchy {

  public static void main(String[] args) {
    
    Thread mainThread = Thread.currentThread();
    ThreadGroup mainGroup = mainThread.getThreadGroup();
    ThreadGroup systemGroup = mainGroup.getParent();

    printThreads(systemGroup, "     ");
  }

  private static void printThreads(ThreadGroup tg, String indent) {
    if(tg.getName().equals("system")) {
      System.out.println(tg.getName() + "(TG)");
    }
    
    Thread[] threads = new Thread[10];
    int size = tg.enumerate(threads, false);
    for(int i = 0; i < size; i++) {
      System.out.println(indent + threads[i].getName() + "(T)");
    }
    
    ThreadGroup[] threadGroups = new ThreadGroup[10];
    size = tg.enumerate(threadGroups, false);
    for(int i = 0; i < size; i++) {
      System.out.println(indent + threadGroups[i].getName() + "(TG)");
      printThreads(threadGroups[i], indent + "     ");
    }
  }

}

// JVM의 스레드 계층도:
// system(TG)
// => Reference Handler(T)
// => Finalizer(T)
// => Signal Dispatcher(T)
// => main(TG)
// ...=> main(T) : main() 메서드를 호출한다.
// => InnocuousThreadGroup(TG)
// ...=> Common-Cleaner(T)
