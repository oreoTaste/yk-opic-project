package yk.opic;

import java.util.Scanner;

public class App {


  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner scanner = new java.util.Scanner(inputStream);

  static final int SIZE = 100;

  public static void main(String[] args) {
    BoardHandler.scanner = scanner;
    LessonHandler.scanner = scanner;
    MemberHandler.scanner = scanner;
    String command;

    System.out.println("===============================================");
    System.out.println("명령어 모음 : /board/add, /board/list\n"
        + "\t/lesson/add, /lesson/list\n\t/member/add, /member/list");
    System.out.println("===============================================");
    
    do {
      System.out.println();
      System.out.print("명령> ");
      command = scanner.nextLine();

      switch (command) {
        case "/lesson/add" :
          LessonHandler.addLesson();
          break;

        case "/lesson/list" :
          LessonHandler.listLesson();
          break;

        case "/member/add" :
          MemberHandler.addMember();
          break;

        case "/member/list" :
          MemberHandler.listMember();
          break;

        case "/board/add" :
          BoardHandler.addBoard();
          break;

        case "/board/list" :
          BoardHandler.listBoard();
          break;

        default : 
          if(!command.equalsIgnoreCase("quit"))
            System.out.println("실행할 수 없는 명령입니다.");
          break;
      }

    } while (!command.equalsIgnoreCase("quit"));
    scanner.close();
    System.out.println("...안녕!");
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