package yk.opic.project.handler;

import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.util.Prompt;

public class LessonUpdateCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonUpdateCommand(LessonDao lessonDao, Prompt prompt) {
    this.lessonDao = lessonDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      Lesson oldLesson = lessonDao.findByNo(prompt.inputInt("번호? "));
      Lesson lesson = new Lesson();

      lesson.setNo(oldLesson.getNo());

      lesson.setTitle(prompt.inputString(
          String.format("수업명? (%s) ", oldLesson.getTitle()),
          oldLesson.getTitle()));

      lesson.setContext(prompt.inputString(
          String.format("수업내용? (%s) ", oldLesson.getContext()),
          oldLesson.getContext()));

      lesson.setStartDate(prompt.inputDate(
          String.format("시작일? (%s) ", oldLesson.getStartDate()),
          oldLesson.getStartDate()));

      lesson.setEndDate(prompt.inputDate(
          String.format("종료일? (%s) ", oldLesson.getEndDate()),
          oldLesson.getEndDate()));

      lesson.setTotalHour(prompt.inputInt(
          String.format("총수업시간? (%d) ", oldLesson.getTotalHour()),
          oldLesson.getTotalHour()));

      lesson.setDailyHour(prompt.inputInt(
          String.format("일수업시간? (%d) ", oldLesson.getDailyHour()),
          oldLesson.getDailyHour()));

      if (lesson.equals(oldLesson)) {
        System.out.println("수업정보 변경을 취소했습니다.");
      } else {
        int index = lessonDao.update(lesson);

        if(index > 0) {
          System.out.println("수업 변경을 완료하였습니다.");
        } else {
          System.out.println("수업 변경을 취소했습니다..");
        }
      }
    } catch(Exception e) {
    }
  }

}
