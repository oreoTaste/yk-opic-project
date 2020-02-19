package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Lesson;

public class LessonDeleteServlet implements Servlet {
  List<Lesson> lessonList;

  public LessonDeleteServlet(List<Lesson> lessonList) {
    this.lessonList = lessonList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = 0;
    for(; index < lessonList.size(); index++) {

      if(lessonList.get(index).getNo() == no) {
        break;
      }

    }

    if(index == lessonList.size()) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      lessonList.remove(index);
      out.writeUTF("OK");
      out.flush();
    }
  }

}
