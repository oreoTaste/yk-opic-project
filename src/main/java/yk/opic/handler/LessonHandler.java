package yk.opic.handler;

import java.util.List;
import yk.opic.domain.Lesson;
import yk.opic.util.Prompt;

public class LessonHandler {
  List<Lesson> lessonList;
  Prompt prompt;

  public LessonHandler(Prompt prompt, List<Lesson> list) {
    this.prompt = prompt;
    this.lessonList = list;
  }


  public void addLesson() {
    Lesson lesson = new Lesson();

    try {
      lesson.setNo(prompt.inputInt("번호? "));
      lesson.setTitle(prompt.inputString("수업명? "));
      lesson.setContext(prompt.inputString("수업내용? "));
      lesson.setStartDate(prompt.inputDate("시작일? (형식 : 2019-01-01) "));
      lesson.setEndDate(prompt.inputDate("종료일? (형식 : 2019-01-01) "));
      lesson.setTotalHour(prompt.inputInt("총수업시간? (형식: 1000) "));
      lesson.setDailyHour(prompt.inputInt("일수업시간? (형식: 8) "));
    } catch(Exception e) {
      return;
    }

    System.out.println();
    lessonList.add(lesson);
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
    int no = prompt.inputInt("번호? ");
    int index = indexOfLesson(no);

    if(index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }

    try {
      Lesson lesson = this.lessonList.get(index);
      System.out.printf("번호? %d\n", lesson.getNo());
      System.out.printf("수업명: %s\n", lesson.getTitle());
      System.out.printf("수업내용: %s\n", lesson.getContext());
      System.out.printf("기간 : %tF ~ %tF\n", lesson.getStartDate(), lesson.getEndDate());
      System.out.printf("총수업시간: %d\n", lesson.getTotalHour());
      System.out.printf("일수업시간: %d\n", lesson.getDailyHour());
    } catch (Exception e) {
      return;
    }
  }


  public void updateLesson() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfLesson(no);

    if(index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }

    Lesson oldLesson = this.lessonList.get(index);
    Lesson newLesson = new Lesson();

    newLesson.setNo(oldLesson.getNo());

    newLesson.setTitle(prompt.inputString(
        String.format("수업명? (%s) ", oldLesson.getTitle()),
        oldLesson.getTitle()));

    newLesson.setContext(prompt.inputString(
        String.format("수업내용? (%s) ", oldLesson.getContext()),
        oldLesson.getContext()));

    newLesson.setStartDate(prompt.inputDate(
        String.format("시작일? (%s) ", oldLesson.getStartDate()),
        oldLesson.getStartDate()));

    newLesson.setEndDate(prompt.inputDate(
        String.format("종료일? (%s) ", oldLesson.getEndDate()),
        oldLesson.getEndDate()));

    newLesson.setTotalHour(prompt.inputInt(
        String.format("총수업시간? (%d) ", oldLesson.getTotalHour()),
        oldLesson.getTotalHour()));

    newLesson.setDailyHour(prompt.inputInt(
        String.format("일수업시간? (%d) ", oldLesson.getDailyHour()),
        oldLesson.getDailyHour()));

    if(newLesson.equals(oldLesson)) {
      System.out.println("수업 변경을 취소했습니다.");
    } else {
      this.lessonList.set(index, newLesson);
      System.out.println("수업을 변경했습니다.");
    }
  }


  public void deleteLesson() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfLesson(no);

    if(index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }

    this.lessonList.remove(index);
    System.out.println("수업을 삭제했습니다.");
  }


  public int indexOfLesson(int no) {
    for(int i = 0 ; i < this.lessonList.size() ; i++) {
      if(lessonList.get(i).getNo() == no)
        return i;
    } return -1;
  }


}
