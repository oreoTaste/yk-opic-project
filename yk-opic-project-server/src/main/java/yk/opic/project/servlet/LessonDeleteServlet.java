package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.service.LessonService;
import yk.opic.util.Prompt;

public class LessonDeleteServlet implements Servlet {
  LessonService lessonService;

  public LessonDeleteServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int no = Prompt.inputInt(in, out, "번호? ");
      int index = lessonService.delete(no);

      if(index > 0) {
        out.println("수업정보를 삭제했습니다.");
      } else {
        out.println("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      out.println("수업정보 삭제중 오류발생!");
      e.printStackTrace();
    }
  }

}
