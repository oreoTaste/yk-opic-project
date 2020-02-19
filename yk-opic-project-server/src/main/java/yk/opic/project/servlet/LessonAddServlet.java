package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Lesson;

public class LessonAddServlet implements Servlet {
  List<Lesson> lessonList;

  public LessonAddServlet(List<Lesson> lessonList) {
    this.lessonList = lessonList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Lesson lesson = (Lesson) in.readObject();

      int index = 0;
      for(; index < lessonList.size(); index++) {

        if(lessonList.get(index).getNo() == lesson.getNo()) {
          break;
        }

      }

      if(index == lessonList.size()) {
        lessonList.add(lesson);
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.flush();
        out.writeUTF("같은 번호의 수업정보가 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

}
