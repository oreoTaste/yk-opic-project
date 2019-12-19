package yk.opic;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    java.io.InputStream inputStream = System.in;
    Scanner scanner = new Scanner(inputStream);

    final int SIZE = 100;
    int[] no = new int [SIZE];
    String[] title = new String [SIZE];
    Date[] today = new Date[SIZE];
    int[] pageOpened = new int[SIZE];
    int count = 0;

    for(int i=0 ; ;i++) {
      count++;
      today[i] = new Date(System.currentTimeMillis());
      pageOpened[i]++;
      System.out.print("번호? ");
      no[i] = scanner.nextInt();
      scanner.nextLine(); // 줄바꿈 기호 제거용

      System.out.print("내용? ");
      title[i] = scanner.nextLine();
      System.out.println();
      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String repeat = scanner.nextLine();
      System.out.println();
      if(repeat.equals("N") || repeat.equals("n")) break;
      else continue;
    } scanner.close();


    System.out.println();
    for(int i=0 ; i<count ; i++) {
      System.out.printf("%d, %s              , %tF, %d\n",
          no[i], title[i], today[i], pageOpened[i]);
    }
  }
}
/*
번호? 1
내용? 게시글입니다.

계속 입력하시겠습니까?(Y/n) y

번호? 2
내용? 두 번째 게시글입니다.

계속 입력하시겠습니까?(Y/n) y

번호? 3
내용? 두 번째 게시글입니다.

계속 입력하시겠습니까?(Y/n) n

1, 게시글입니다.              , 2019-01-01, 0
2, 두 번째 게시글입니다.        , 2019-01-01, 0
3, 세 번째 게시글입니다.        , 2019-01-01, 0
 */