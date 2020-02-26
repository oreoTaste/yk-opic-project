package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.json.LessonJsonFileDao;

public class LessonDeleteServlet implements Servlet {
  LessonJsonFileDao lessonDao;

  public LessonDeleteServlet(LessonJsonFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = lessonDao.delete(no);

    if(index == 0) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      out.writeUTF("OK");
      out.flush();
    }
  }

}
