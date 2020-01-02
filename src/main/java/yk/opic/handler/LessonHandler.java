package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Lesson;

public class LessonHandler {
  ArrayList lessonList;
  Scanner input;

  public LessonHandler(Scanner input) {
    this.input = input;
    lessonList = new ArrayList();
  }

  public LessonHandler(Scanner input, int capacity) {
    this.input = input;
    lessonList = new ArrayList(capacity);
  }

  public void addLesson() {
    Object object = new Lesson();
    Lesson les = (Lesson)object;
    System.out.print("번호? ");
    les.setNo(input.nextInt());
    input.nextLine();
    System.out.print("수업명? ");
    les.setTitle(input.nextLine());
    System.out.print("수업내용? ");
    les.setContext(input.nextLine());
    System.out.print("시작일? (형식 : 2019-01-01) ");
    les.setStartDate(Date.valueOf(input.nextLine()));
    System.out.print("종료일? (형식 : 2019-01-01) ");
    les.setEndDate(Date.valueOf(input.nextLine()));
    System.out.print("총수업시간? (형식: 1000) ");
    les.setTotalHour(input.nextInt());
    System.out.print("일수업시간? (형식: 8) ");
    les.setDailyHour(input.nextInt());
    System.out.println();
    input.nextLine();

    lessonList.add(les);
  }

  public void listLesson() {
    Object[] object = lessonList.toArray();
    for(Object lsn : object) {
      Lesson ls = (Lesson)lsn;
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n",
          ls.getNo(), ls.getTitle(), ls.getStartDate(),
          ls.getEndDate(), ls.getTotalHour());
    }
  }

}
