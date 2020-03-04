package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.util.Prompt;

public class LessonUpdateServlet implements Servlet {
  LessonDao lessonDao;

  public LessonUpdateServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      Lesson oldLesson = lessonDao.findByNo(Prompt.inputInt(in, out, "번호? "));
      Lesson lesson = new Lesson();

      lesson.setNo(oldLesson.getNo());

      lesson.setTitle(Prompt.inputString( in, out,
          String.format("수업명? (%s) ", oldLesson.getTitle()),
          oldLesson.getTitle()));

      lesson.setContext(Prompt.inputString( in, out,
          String.format("수업내용? (%s) ", oldLesson.getContext()),
          oldLesson.getContext()));

      lesson.setStartDate(Prompt.inputDate( in, out,
          String.format("시작일? (%s) ", oldLesson.getStartDate()),
          oldLesson.getStartDate()));

      lesson.setEndDate(Prompt.inputDate( in, out,
          String.format("종료일? (%s) ", oldLesson.getEndDate()),
          oldLesson.getEndDate()));

      lesson.setTotalHour(Prompt.inputInt( in, out,
          String.format("총수업시간? (%d) ", oldLesson.getTotalHour()),
          oldLesson.getTotalHour()));

      lesson.setDailyHour(Prompt.inputInt( in, out,
          String.format("일수업시간? (%d) ", oldLesson.getDailyHour()),
          oldLesson.getDailyHour()));

      if (lesson.equals(oldLesson)) {
        out.println("수업정보 변경을 취소했습니다.");
      } else {
        int index = lessonDao.update(lesson);

        if(index > 0) {
          out.println("수업 변경을 완료하였습니다.");
        } else {
          out.println("수업 변경을 취소했습니다..");
        }
      }
    } catch(Exception e) {
      out.println("수업 변경중 오류발생!");
    }

  }

}
