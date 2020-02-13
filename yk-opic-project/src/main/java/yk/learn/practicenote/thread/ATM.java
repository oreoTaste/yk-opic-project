package yk.learn.practicenote.thread;

public class ATM implements Runnable {
  String name;
  Account account;

  public ATM(String name, Account account) {
    this.name = name;
    this.account = account;
  }

  @Override
  public void run() {
    long money = 0;
    long sum = 0;

    while (true) {
      try {
        money = account.withdraw(1_000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (money <= 0) {
        break;
      }
      sum += money;
    }
    System.out.printf("%s에서 찾은 돈: %d원\n", this.name, sum);
  }
}



