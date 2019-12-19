package yk.opic;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    // 키보드에서 사용자가 입력한 값을 읽어 
    // 문자열이나 정수, 부동소수점 등으로 리턴하는 역할
    Scanner keyboard = new Scanner(System.in);
    
    final int SIZE = 100;

    class Lesson {
      int no;
      String title;
      String description;
      Date startDate;
      Date endDate;
      int totalHours;
      int dayHours;
    }
    
    Lesson[] lessons = new Lesson[SIZE];
    int count = 0;

    for(int i=0 ; ; i++) {
      Lesson ls = new Lesson();
      count++;
      System.out.print("번호? ");
      ls.no = keyboard.nextInt();

      keyboard.nextLine(); // nextInt() 후에 남아 있는 줄바꿈 기호를 제거한다.

      System.out.print("수업명? ");
      ls.title = keyboard.nextLine();

      System.out.print("수업내용? ");
      ls.description = keyboard.nextLine();

      System.out.print("시작일? ");
      ls.startDate = Date.valueOf(keyboard.next());

      System.out.print("종료일? ");
      ls.endDate = Date.valueOf(keyboard.next());

      System.out.print("총수업시간? ");
      ls.totalHours = keyboard.nextInt();

      System.out.print("일수업시간? ");
      ls.dayHours = keyboard.nextInt();
      keyboard.nextLine(); // 빈칸제거 (커서이동)
      System.out.println();
      lessons[i] = ls;
      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String repeat = keyboard.nextLine();
      System.out.println();
      if(repeat.equals("N") || repeat.equals("n")) break;
      else continue;
    } keyboard.close();

    for(int i=0 ; i<count ; i++){
      Lesson ls = new Lesson();
      ls = lessons[i];
      System.out.printf("%d, %s    , %tF ~ %tF, %d\n",
         ls.no, ls.title, ls.startDate, ls.endDate, ls.totalHours);
    }
  }
}
/*

번호? 1
수업명? 자바 프로젝트 실습
수업내용? 자바 프로젝트를 통한 자바 언어 활용법 익히기
시작일? 2019-01-02
종료일? 2019-05-28
총수업시간? 1000
일수업시간? 8

계속 입력하시겠습니까?(Y/n) y

번호? 2
수업명? 자바 프로그래밍 기초
수업내용? 자바 언어 기초 문법을 학습하기
시작일? 2019-02-01
종료일? 2019-02-28
총수업시간? 160
일수업시간? 8

계속 입력하시겠습니까?(Y/n) y

번호? 3
수업명? 자바 프로그래밍 고급
수업내용? 디자인 패턴과 리랙토링 기법 학습하기
시작일? 2019-03-02
종료일? 2019-03-30
총수업시간? 160
일수업시간? 8

계속 입력하시겠습니까?(Y/n) n

1, 자바 프로젝트 실습     , 2019-01-02 ~ 2019-05-28, 1000
2, 자바 프로그래밍 기초    , 2019-02-01 ~ 2019-02-28,  160
3, 자바 프로그래밍 고급    , 2019-03-02 ~ 2019-03-30,  160
 
 */





