package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Lesson;

public class LessonUpdateServlet implements Servlet {
  List<Lesson> lessonList;

  public LessonUpdateServlet(List<Lesson> lessonList) {
    this.lessonList = lessonList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = new Lesson();
    lesson = (Lesson) in.readObject();

    int index = 0;
    for(; index < lessonList.size(); index++) {

      if(lessonList.get(index).getNo() == lesson.getNo()) {
        break;
      }

    }

    if(index == lessonList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업정보가 없습니다.");
      out.flush();
    } else {
      lessonList.set(index, lesson);
      out.writeUTF("OK");
      out.flush();
    }
  }

}
