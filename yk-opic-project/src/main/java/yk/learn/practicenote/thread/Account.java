package yk.learn.practicenote.thread;

import java.util.concurrent.Semaphore;

public class Account {
  String accountId;
  long balance;
  static Semaphore semaphore = new Semaphore(1);

  public Account(String accountId, long balance) {
    this.accountId = accountId;
    this.balance = balance;
  }
  
  /*synchronized */public long withdraw(long money) throws InterruptedException {
    try {
    semaphore.acquire();
    long bal = this.balance;
    bal -= money;
    if (bal < 0)
      return 0;
    this.balance = bal;
    //Thread.sleep(8); // time lag
    return money;
    } finally {
      semaphore.release();
    }
  }
}


