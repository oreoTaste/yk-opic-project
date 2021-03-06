package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.Lesson;
import yk.opic.project.service.LessonService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/lesson/detail")
public class LessonDetailServlet implements Servlet {
  LessonService lessonService;

  public LessonDetailServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {

      int no = Prompt.inputInt(in, out, "번호? ");
      Lesson lesson = lessonService.get(no);
      if(lesson != null) {
        out.printf("번호? %d\n", lesson.getNo());
        out.printf("수업명: %s\n", lesson.getTitle());
        out.printf("수업내용: %s\n", lesson.getContext());
        out.printf("기간 : %tF ~ %tF\n", lesson.getStartDate(), lesson.getEndDate());
        out.printf("총수업시간: %d\n", lesson.getTotalHour());
        out.printf("일수업시간: %d\n", lesson.getDailyHour());
      } else {
        out.println("해당 번호의 수업정보가 없습니다.");
      }
    } catch(Exception e) {
      out.println("수업정보 조회중 오류발생!");
      e.printStackTrace();
    }
  }

}
