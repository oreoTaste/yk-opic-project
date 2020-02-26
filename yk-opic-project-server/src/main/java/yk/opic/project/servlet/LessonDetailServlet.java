package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.LessonObjectFileDao;
import yk.opic.project.domain.Lesson;

public class LessonDetailServlet implements Servlet {
  LessonObjectFileDao lessonDao;

  public LessonDetailServlet(LessonObjectFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Lesson lesson = lessonDao.findByNo(no);
    

    if(lesson == null) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업정보가 없습니다.");
      out.flush();
    } else {
      out.writeUTF("OK");
      out.flush();
      out.reset();
      out.writeObject(lesson);
      out.flush();
    }
  }

}
