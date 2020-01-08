package yk.opic;

import yk.opic.handler.BoardHandler;
import yk.opic.handler.LessonHandler;
import yk.opic.handler.MemberHandler;

public class App {

  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner scanner = new java.util.Scanner(inputStream);

  static final int SIZE = 100;

  public static void main(String[] args) {
    BoardHandler boardHandler = new BoardHandler(scanner);
    BoardHandler boardHandler2 = new BoardHandler(scanner);
    LessonHandler lessonHandler = new LessonHandler(scanner);
    MemberHandler memberHandler = new MemberHandler(scanner);

    String command;

    System.out.println("===============================================");
    System.out.print("명령어 모음 : /board/add, /../list, /../delete\n"
        + "\t\t     /../update, /../detail\n"
        + "\t/lesson/add, /../list, /../delete\n"
        + "\t\t     /../update, /../detail\n"
        + "\t/member/add, /../list, /../delete\n"
        + "\t\t     /../update, /../detail\n");
    System.out.println("===============================================");

    do {
      System.out.println();
      System.out.print("명령> ");
      command = scanner.nextLine();

      switch (command) {
        case "/lesson/add" :
          lessonHandler.addLesson();
          break;

        case "/lesson/list" :
          lessonHandler.listLesson();
          break;

          //새로운 기능 추가
        case "/lesson/delete" :
          lessonHandler.deleteLesson();
          break;

        case "/lesson/update" :
          lessonHandler.updateLesson();
          break;

        case "/lesson/detail" :
          lessonHandler.detailLesson();
          break;

        case "/member/add" :
          memberHandler.addMember();
          break;

        case "/member/list" :
          memberHandler.listMember();
          break;

          // 새로운 기능 추가
        case "/member/delete" :
          memberHandler.deleteMember();
          break;

        case "/member/update" :
          memberHandler.updateMember();
          break;

        case "/member/detail" :
          memberHandler.detailMember();
          break;

        case "/board/add" :
          boardHandler.addBoard();
          break;

        case "/board/list" :
          boardHandler.listBoard();
          break;

          // 새로운 기능 추가
        case "/board/delete" :
          boardHandler.deleteBoard();
          break;

        case "/board/update" :
          boardHandler.updateBoard();
          break;

        case "/board/detail" :
          boardHandler.detailBoard();
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