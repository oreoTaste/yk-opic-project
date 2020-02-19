package yk.opic.project.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Lesson;

public class LessonListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public LessonListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {

    try {
      out.writeUTF("/lesson/list");
      out.flush();

      String response = in.readUTF();

      if(response.equalsIgnoreCase("OK")) {
        List<Lesson> lesson = (List<Lesson>) in.readObject();

        for (Lesson ls : lesson) {
          System.out.printf("%d, %s     , %tF ~ %tF, %d\n",
              ls.getNo(),
              ls.getTitle(),
              ls.getStartDate(),
              ls.getEndDate(),
              ls.getTotalHour());
        }
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }


}
