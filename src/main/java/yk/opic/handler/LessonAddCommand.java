package yk.opic.handler;

import java.util.List;
import yk.opic.domain.Lesson;
import yk.opic.util.Prompt;

public class LessonAddCommand implements Command {
  List<Lesson> lessonList;
  Prompt prompt;

  public LessonAddCommand(Prompt prompt, List<Lesson> list) {
    this.prompt = prompt;
    lessonList = list;
  }

  @Override
  public void execute() {
    Lesson lesson = new Lesson();

    try {
      lesson.setNo(prompt.inputInt("번호? "));
      lesson.setTitle(prompt.inputString("수업명? "));
      lesson.setContext(prompt.inputString("수업내용? "));
      lesson.setStartDate(prompt.inputDate("시작일? (형식 : 2019-01-01) "));
      lesson.setEndDate(prompt.inputDate("종료일? (형식 : 2019-01-01) "));
      lesson.setTotalHour(prompt.inputInt("총수업시간? (형식: 1000) "));
      lesson.setDailyHour(prompt.inputInt("일수업시간? (형식: 8) "));
    } catch (Exception e) {
      return;
    }

    System.out.println();
    lessonList.add(lesson);
  }


}
