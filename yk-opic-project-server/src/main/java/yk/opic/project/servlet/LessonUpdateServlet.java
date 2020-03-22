package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.Lesson;
import yk.opic.project.service.LessonService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/lesson/update")
public class LessonUpdateServlet implements Servlet {
  LessonService lessonService;

  public LessonUpdateServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      Lesson oldLesson = lessonService.get(Prompt.inputInt(in, out, "번호? "));
      Lesson lesson = new Lesson();

      lesson.setNo(oldLesson.getNo());

      lesson.setTitle(Prompt.inputString( in, out,
          String.format("수업명? (%s) ", oldLesson.getTitle()),
          oldLesson.getTitle()));

      lesson.setContext(Prompt.inputString( in, out,
          String.format("수업내용? (%s) ", oldLesson.getContext()),
          oldLesson.getContext()));

      lesson.setStartDate(Prompt.inputDate( in, out,
          String.format("시작일? (%s) ", oldLesson.getStartDate())));

      lesson.setEndDate(Prompt.inputDate( in, out,
          String.format("종료일? (%s) ", oldLesson.getEndDate())));

      lesson.setTotalHour(Prompt.inputInt( in, out,
          String.format("총수업시간? (%d) ", oldLesson.getTotalHour())));

      lesson.setDailyHour(Prompt.inputInt( in, out,
          String.format("일수업시간? (%d) ", oldLesson.getDailyHour())));

      if (lesson.equals(oldLesson)) {
        out.println("수업정보 변경을 취소했습니다.");
      } else {
        int index = lessonService.update(lesson);

        if(index > 0) {
          out.println("수업 변경을 완료하였습니다.");
        } else {
          out.println("수업 변경을 취소했습니다..");
        }
      }
    } catch(Exception e) {
      out.println("수업 변경중 오류발생!");
      e.printStackTrace();
    }

  }

}
