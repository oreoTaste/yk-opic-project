package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Lesson;
import yk.opic.util.ArrayList;

public class LessonHandler {
  ArrayList<Lesson> lessonList;
  Scanner input;

  public LessonHandler(Scanner input) {
    this.input = input;
    lessonList = new ArrayList<>();
  }

  public LessonHandler(Scanner input, int capacity) {
    this.input = input;
    lessonList = new ArrayList<>(capacity);
  }

  public void addLesson() {
    Lesson les = new Lesson();
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
    Lesson[] lesson = new Lesson[this.lessonList.size()];
    lessonList.toArray(lesson);
    for(Lesson ls : lesson) {
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n",
          ls.getNo(), ls.getTitle(), ls.getStartDate(),
          ls.getEndDate(), ls.getTotalHour());
    }
  }
  
  public void detailLesson() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Lesson les = this.lessonList.get(tempIndex);
    if(les == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }
    
    System.out.printf("번호? %d\n", les.getNo());
    System.out.printf("수업명: %s\n", les.getTitle());
    System.out.printf("수업내용: %s\n", les.getContext());
    System.out.printf("기간 : %tF ~ %tF\n", les.getStartDate(), les.getEndDate());
    System.out.printf("총수업시간: %d\n", les.getTotalHour());
    System.out.printf("일수업시간: %d\n", les.getDailyHour());
  }

  public void updateLesson() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Lesson oldValue = this.lessonList.get(tempIndex);
    if(oldValue == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }

    Lesson newValue = new Lesson();
    boolean changed = true;

    System.out.printf("번호? %d\n", oldValue.getNo());
      newValue.setNo(oldValue.getNo());
    
    System.out.printf("수업명? (%s) ", oldValue.getTitle());
    String tempTitle= input.nextLine();
    if(tempTitle.length() == 0) {
      changed = false;
      newValue.setTitle(oldValue.getTitle());
    }
    newValue.setTitle(tempTitle);
    
    System.out.printf("수업내용? (%s) ", oldValue.getContext());
    String tempContext= input.nextLine();
    if(tempContext.length() == 0) {
      changed = false;
      newValue.setContext(oldValue.getContext());
    }
    newValue.setContext(tempContext);
    
    System.out.printf("시작일? (%s) ", oldValue.getStartDate());
    String tempStartDate= input.nextLine();
    if(tempStartDate.length() == 0) {
      changed = false;
      newValue.setStartDate(oldValue.getStartDate());
    }
    newValue.setStartDate(Date.valueOf(tempStartDate));
    
    System.out.printf("종료일? (%s) ", oldValue.getEndDate());
    String tempEndDate= input.nextLine();
    if(tempEndDate.length() == 0) {
      changed = false;
      newValue.setEndDate(oldValue.getEndDate());
    }
    newValue.setEndDate(Date.valueOf(tempEndDate));
    
    System.out.printf("총수업시간? (%d) ", oldValue.getTotalHour());
    String tempTotalHour = input.nextLine();
    if(tempTotalHour.length() == 0) {
      changed = false;
      newValue.setTotalHour(oldValue.getTotalHour());
    }
    newValue.setTotalHour(Integer.valueOf(tempTotalHour));
    
    System.out.printf("일수업시간? (%d) ", oldValue.getDailyHour());
    String tempDailyHour = input.nextLine();
    if(tempDailyHour.length() == 0) {
      changed = false;
      newValue.setDailyHour(oldValue.getDailyHour());
    }
    newValue.setDailyHour(Integer.valueOf(tempDailyHour));   
    
    if(changed) {
      this.lessonList.set(tempIndex, newValue);
      System.out.println("수업을 변경했습니다.");
    }
    else System.out.println("수업 변경을 취소했습니다.");
    
  }
  
  public void deleteLesson() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Lesson les = this.lessonList.get(tempIndex);
    if(les == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }

    this.lessonList.remove(tempIndex);
    System.out.println("수업을 삭제했습니다.");
  }
}
