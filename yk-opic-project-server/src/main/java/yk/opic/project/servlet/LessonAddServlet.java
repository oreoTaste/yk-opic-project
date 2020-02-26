package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.LessonObjectFileDao;
import yk.opic.project.domain.Lesson;

public class LessonAddServlet implements Servlet {
  LessonObjectFileDao lessonDao;

  public LessonAddServlet(LessonObjectFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      
      Lesson lesson = (Lesson) in.readObject();
      int index = lessonDao.insert(lesson);

      if(index > 0) {
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 수업정보가 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

}
