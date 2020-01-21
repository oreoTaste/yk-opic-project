package yk.opic.handler;

import java.util.List;
import yk.opic.domain.Lesson;
import yk.opic.util.Prompt;

public class LessonUpdateCommand implements Command {
  List<Lesson> lessonList;
  Prompt prompt;

  public LessonUpdateCommand(Prompt prompt, List<Lesson> list) {
    this.prompt = prompt;
    lessonList = list;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfLesson(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }

    Lesson oldLesson = lessonList.get(index);
    Lesson newLesson = new Lesson();

    newLesson.setNo(oldLesson.getNo());

    newLesson.setTitle(prompt.inputString(String.format("수업명? (%s) ", oldLesson.getTitle()),
        oldLesson.getTitle()));

    newLesson.setContext(prompt.inputString(String.format("수업내용? (%s) ", oldLesson.getContext()),
        oldLesson.getContext()));

    newLesson.setStartDate(prompt.inputDate(String.format("시작일? (%s) ", oldLesson.getStartDate()),
        oldLesson.getStartDate()));

    newLesson.setEndDate(prompt.inputDate(String.format("종료일? (%s) ", oldLesson.getEndDate()),
        oldLesson.getEndDate()));

    newLesson.setTotalHour(prompt.inputInt(String.format("총수업시간? (%d) ", oldLesson.getTotalHour()),
        oldLesson.getTotalHour()));

    newLesson.setDailyHour(prompt.inputInt(String.format("일수업시간? (%d) ", oldLesson.getDailyHour()),
        oldLesson.getDailyHour()));

    if (newLesson.equals(oldLesson)) {
      System.out.println("수업 변경을 취소했습니다.");
    } else {
      lessonList.set(index, newLesson);
      System.out.println("수업을 변경했습니다.");
    }
  }

  public int indexOfLesson(int no) {
    for (int i = 0; i < lessonList.size(); i++) {
      if (lessonList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }


}
