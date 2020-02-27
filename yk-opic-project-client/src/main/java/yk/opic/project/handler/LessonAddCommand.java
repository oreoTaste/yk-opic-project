package yk.opic.project.handler;

import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.util.Prompt;

public class LessonAddCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonAddCommand(LessonDao lessonDao, Prompt prompt) {
    this.lessonDao = lessonDao;
    this.prompt = prompt;
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

    try {
      int index = lessonDao.insert(lesson);

      if(index > 0) {
        System.out.println("수업정보를 등록하였습니다");
      } else {
        System.out.println("해당 수업정보가 이미 존재합니다.");
      }
    } catch (Exception e) {
      System.out.println("수업정도 등록중 오류발생!");
      e.printStackTrace();
    }
  }


}
