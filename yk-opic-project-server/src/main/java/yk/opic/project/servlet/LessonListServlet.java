package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.domain.Lesson;
import yk.opic.service.LessonService;

public class LessonListServlet implements Servlet {
  LessonService lessonService;

  public LessonListServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      List<Lesson> lesson = lessonService.list();

      if(lesson != null) {
        for (Lesson ls : lesson) {
          out.printf("%d, %s     , %tF ~ %tF, %d\n",
              ls.getNo(),
              ls.getTitle(),
              ls.getStartDate(),
              ls.getEndDate(),
              ls.getTotalHour());
        }
      } else {
        System.out.println("수업정보가 없습니다.");
      }
    } catch(Exception e) {
      System.out.println("수업정보 불러오기 실패!");
      e.printStackTrace();
    }
  }

}
