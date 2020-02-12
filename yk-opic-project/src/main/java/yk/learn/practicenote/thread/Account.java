package yk.learn.practicenote.thread;

public class Account {
  String accountId;
  long balance;

  public Account(String accountId, long balance) {
    this.accountId = accountId;
    this.balance = balance;
  }
  

  /*synchronized */public long withdraw(long money) throws InterruptedException {
    
    long bal = this.balance;
    bal -= money;
    if (bal < 0)
      return 0;
    this.balance = bal;
    Thread.sleep(8); // time lag
    return money;
  }
}


