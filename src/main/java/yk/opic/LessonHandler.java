package yk.opic;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Lesson;

public class LessonHandler {
  static final int SIZE = 100;
  static int lessonsCount = 0;
  static Lesson[] lessons = new Lesson[SIZE];
  static Scanner scanner;
  
  static void addLesson() {
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
    System.out.println();
    scanner.nextLine();
    
    lessons[lessonsCount++] = les;
    System.out.println("저장하였습니다.");
  }
  static void listLesson() {
    for(int i=0 ; i<lessonsCount ; i++){
      Lesson ls = lessons[i];
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n", ls.no, ls.title, ls.startDate, ls.endDate, ls.totalHour);
    }
  }
  
}
