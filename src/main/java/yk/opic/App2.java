package yk.opic;

import java.sql.Date;
import java.util.Scanner;

public class App2 { 
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    final int SIZE = 100;
    class Lesson {
      Date registeredDate;
      int no;
      String name;
      String email;
      String password;
      String photo;
      String tel;
    }
    Lesson[] lesson = new Lesson[SIZE];
    int count = 0;

    for(int i=0 ; ; i++) {
      Lesson ls = new Lesson();
      count++;
      ls.registeredDate = new Date(System.currentTimeMillis());
      System.out.print("번호? ");
      ls.no = keyboard.nextInt();

      keyboard.nextLine(); // nextInt() 후에 남아 있는 줄바꿈 기호를 제거한다.

      System.out.print("이름? ");
      ls.name = keyboard.nextLine();

      System.out.print("이메일? ");
      ls.email = keyboard.nextLine();

      System.out.print("암호? ");
      ls.password = keyboard.nextLine();

      System.out.print("사진? ");
      ls.photo = keyboard.nextLine();

      System.out.print("전화? ");
      ls.tel = keyboard.nextLine();

      System.out.println();
      lesson[i] = ls;

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String repeat = keyboard.nextLine();
      System.out.println();
      if(repeat.equals("N") || repeat.equals("n")) break;
      else continue;
    } keyboard.close();

    for(int i=0 ; i<count ; i++){
      Lesson ls = new Lesson();
      ls = lesson[i];
      System.out.printf("%d, %s , %s       , %s      , %tF\n",
          ls.no, ls.name, ls.email, ls.tel, ls.registeredDate);
    }
  }
}
/*

번호? 1
이름? 홍길동
이메일? hong@test.com
암호? 1111
사진? hong.png
전화? 1111-2222

계속 입력하시겠습니까?(Y/n) y

번호? 2
이름? 임꺽정
이메일? lim@test.com
암호? 1111
사진? lim.png
전화? 1111-2223

계속 입력하시겠습니까?(Y/n) y

번호? 3
이름? 전봉준
이메일? jeon@test.com
암호? 1111
사진? jeon.png
전화? 1111-2224

계속 입력하시겠습니까?(Y/n) n

1, 홍길동 , hong@test.com       , 1111-2222      , 2019-01-01
2, 임꺽정 , lim@test.com        , 1111-2223      , 2019-01-01
3, 전봉준 , jeon@test.com       , 1111-2224      , 2019-01-01

*/
