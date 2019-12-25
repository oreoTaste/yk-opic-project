package yk.opic;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Lesson;

public class LessonHandler {
  // 인스턴스 변수로 관리 (개별관리)
  Lesson[] lessons = new Lesson[SIZE];
  int lessonsCount = 0;
  
  // 스태틱 필드로 관리 (공통관리)
  static final int SIZE = 100;
  static Scanner scanner;
  
  static void addLesson(LessonHandler lh) {
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
    
    lh.lessons[lh.lessonsCount++] = les;
    System.out.println("저장하였습니다.");
  }
  static void listLesson(LessonHandler lh) {
    for(int i=0 ; i<lh.lessonsCount ; i++){
      Lesson ls = lh.lessons[i];
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n", ls.no, ls.title, ls.startDate, ls.endDate, ls.totalHour);
    }
  }
  
}
