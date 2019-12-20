package yk.opic;

import java.util.Calendar;
import java.sql.Date;
import java.util.Scanner;   

public class App {
  public static void main(String[] args) {

    class Lesson {
      int no;
      String title;
      Date date;
      int viewCount;
      String context;
      Date startDate;
      Date endDate;
      int totalHour;
      int dailyHour;
    }

    class Board {
      int no;
      String title;
      Date date;
      int viewCount;
    }

    class Member {
      int no;
      String name;
      String email;
      String password;
      String photo;
      String tel;
      Date registeredDate;
    }

    final int SIZE = 100;

    Lesson[] lessons = new Lesson[SIZE];
    int lessonsCount = 0;
    Member[] members = new Member[SIZE];
    int membersCount = 0;
    Board[] boards = new Board[SIZE];
    int boardsCount = 0;

    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream);
    String command;

    System.out.println("===============================================");
    System.out.println("명령어 모음 : /board/add, /board/list\n"
        + "\t/lesson/add, /lesson/list\n\t/member/add, /member/list");
    System.out.println("===============================================");
    do {    
      System.out.print("명령> ");
      command = scanner.nextLine();

      switch(command) {
        case "/board/add" :
          Board board = new Board();
          System.out.print("번호? ");
          board.no = scanner.nextInt();
          scanner.nextLine(); // 줄바꿈 기호 제거용

          System.out.print("내용? ");
          board.title = scanner.nextLine();

          board.date = new Date(System.currentTimeMillis());
          board.viewCount    = 0;

          boards[boardsCount++] = board;
          System.out.println("저장하였습니다.");
          break;

        case "/board/list" :
          for (int i = 0; i < boardsCount; i++) {
            Board b = boards[i];
            System.out.printf("%d, %s, %s, %d\n", 
                b.no, b.title, b.date, b.viewCount);
          } break;

        case "/lesson/add" :
          Lesson les = new Lesson();
          System.out.print("번호? ");
          les.no = scanner.nextInt();
          scanner.nextLine();
          System.out.print("수업명? ");
          les.title = scanner.nextLine();
          System.out.print("수업내용? ");
          les.context = scanner.nextLine();
          System.out.print("시작일? (형식 : 2019-01-01) ");
          les.startDate = Date.valueOf(scanner.nextLine());
          System.out.print("종료일? (형식 : 2019-01-01) ");
          les.endDate = Date.valueOf(scanner.nextLine());
          System.out.print("총수업시간? (형식: 1000) ");
          les.totalHour = scanner.nextInt();
          System.out.print("일수업시간? (형식: 8) ");
          les.dailyHour = scanner.nextInt();
          scanner.nextLine();
          lessons[lessonsCount++] = les;
          System.out.println("저장하였습니다.");
          break;

        case "/lesson/list" :
          for(int i=0 ; i<lessonsCount ; i++){
            Lesson l = lessons[i];
            System.out.printf("%d, %s     , %tF ~ %tF, %d\n", l.no, l.title, l.startDate, l.endDate, l.totalHour);
          } break;

        case "/member/add":
          Member mem = new Member();
          System.out.print("번호? ");
          mem.no = scanner.nextInt();
          scanner.nextLine(); // 빈칸제거
          System.out.print("이름? ");
          mem.name = scanner.nextLine();
          System.out.print("이메일? ");
          mem.email = scanner.nextLine();
          System.out.print("비밀번호? ");
          mem.password = scanner.nextLine();
          System.out.print("사진? ");
          mem.photo = scanner.nextLine();
          System.out.print("전화? ");
          mem.tel = scanner.nextLine();
          mem.registeredDate = new Date(System.currentTimeMillis());
          members[membersCount++] = mem;
          System.out.println("저장하였습니다.");
          break;

        case "/member/list":
          for(int i=0 ; i<membersCount ; i++) {
            Member m = members[i];
            System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
                m.no, m.name,  m.email,  m.tel , m.registeredDate);
          }
          break;

        default :
          if(!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
            break;
          }

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