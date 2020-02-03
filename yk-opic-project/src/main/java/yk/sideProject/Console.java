package yk.sideProject;

import java.util.Scanner;

public class Console {
  static int inputInt(String msg) {
    java.io.InputStream inputStream = System.in;
    Scanner scanner = new Scanner (inputStream);
    System.out.print(msg);
    int base = scanner.nextInt();
    //scanner.close(); 연속사용을 위해 일단 보류
    return base;
  }

  //Deprecated 활용연습
  @Deprecated
  static int inputInt() {
    Scanner scanner = new Scanner (System.in);
    System.out.print("밑변 길이? ");
    int base = scanner.nextInt();
    scanner.close();
    return (base & 0x01)==0x01 ? base-1 : base;
  }
}
