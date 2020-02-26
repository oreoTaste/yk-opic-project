package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;

public class LessonUpdateServlet implements Servlet {
  LessonDao lessonDao;

  public LessonUpdateServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = new Lesson();
    lesson = (Lesson) in.readObject();

    int index = lessonDao.update(lesson);

    if(index == 0) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업정보가 없습니다.");
      out.flush();
    } else {
      out.writeUTF("OK");
      out.flush();
    }
  }

}
