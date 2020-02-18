package yk.opic.project.client.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.client.domain.Lesson;
import yk.opic.project.client.util.Prompt;

public class LessonAddCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public LessonAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Lesson lesson = new Lesson();

    try {
      lesson.setNo(prompt.inputInt("번호? "));
      lesson.setTitle(prompt.inputString("수업명? "));
      lesson.setContext(prompt.inputString("수업내용? "));
      lesson.setStartDate(prompt.inputDate("시작일? (형식 : 2019-01-01) "));
      lesson.setEndDate(prompt.inputDate("종료일? (형식 : 2019-01-01) "));
      lesson.setTotalHour(prompt.inputInt("총수업시간? (형식: 1000) "));
      lesson.setDailyHour(prompt.inputInt("일수업시간? (형식: 8) "));
    } catch (Exception e) {
      return;
    }

    try {
      out.writeUTF("/lesson/add");
      out.writeObject(lesson);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        System.out.println("저장완료");
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
