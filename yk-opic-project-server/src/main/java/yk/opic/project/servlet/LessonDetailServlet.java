package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Lesson;

public class LessonDetailServlet implements Servlet {
  List<Lesson> lessonList;

  public LessonDetailServlet(List<Lesson> lessonList) {
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
      out.writeUTF("해당 번호의 수업정보가 없습니다.");
    } else {
      out.writeUTF("OK");
      out.writeObject(lessonList.get(index));
    }
  }

}
