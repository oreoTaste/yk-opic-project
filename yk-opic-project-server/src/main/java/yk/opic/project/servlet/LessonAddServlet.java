package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.Lesson;
import yk.opic.project.service.LessonService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/lesson/add")
public class LessonAddServlet implements Servlet {
  LessonService lessonService;

  public LessonAddServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Lesson lesson = new Lesson();

    try {
      lesson.setTitle(Prompt.inputString(in, out, "수업명? "));
      lesson.setContext(Prompt.inputString(in, out, "수업내용? "));
      lesson.setStartDate(Prompt.inputDate(in, out, "시작일? (형식 : 2019-01-01) "));
      lesson.setEndDate(Prompt.inputDate(in, out, "종료일? (형식 : 2019-01-01) "));
      lesson.setTotalHour(Prompt.inputInt(in, out, "총수업시간? (형식: 1000) "));
      lesson.setDailyHour(Prompt.inputInt(in, out, "일수업시간? (형식: 8) "));

      int index = lessonService.add(lesson);
      if(index > 0) {
        out.println("수업정보를 등록하였습니다");
      } else {
        out.println("해당 수업정보가 이미 존재합니다.");
      }
    } catch (Exception e) {
      out.println("수업정도 등록중 오류발생!");
      e.printStackTrace();
    }
  }

}
