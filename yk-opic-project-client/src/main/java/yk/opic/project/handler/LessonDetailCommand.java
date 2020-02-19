package yk.opic.project.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.domain.Lesson;
import yk.opic.project.util.Prompt;

public class LessonDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public LessonDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }


  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      out.writeUTF("/lesson/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        Lesson lesson = (Lesson) in.readObject();

        System.out.printf("번호? %d\n", lesson.getNo());
        System.out.printf("수업명: %s\n", lesson.getTitle());
        System.out.printf("수업내용: %s\n", lesson.getContext());
        System.out.printf("기간 : %tF ~ %tF\n", lesson.getStartDate(), lesson.getEndDate());
        System.out.printf("총수업시간: %d\n", lesson.getTotalHour());
        System.out.printf("일수업시간: %d\n", lesson.getDailyHour());
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

}
