package yk.opic.project.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.domain.Lesson;
import yk.opic.project.util.Prompt;

public class LessonUpdateCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public LessonUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

      if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      } else if(response.equalsIgnoreCase("OK")) {

        Lesson oldLesson = (Lesson) in.readObject();
        Lesson newLesson = new Lesson();

        newLesson.setNo(oldLesson.getNo());

        newLesson.setTitle(prompt.inputString(
            String.format("수업명? (%s) ", oldLesson.getTitle()),
            oldLesson.getTitle()));

        newLesson.setContext(prompt.inputString(
            String.format("수업내용? (%s) ", oldLesson.getContext()),
            oldLesson.getContext()));

        newLesson.setStartDate(prompt.inputDate(
            String.format("시작일? (%s) ", oldLesson.getStartDate()),
            oldLesson.getStartDate()));

        newLesson.setEndDate(prompt.inputDate(
            String.format("종료일? (%s) ", oldLesson.getEndDate()),
            oldLesson.getEndDate()));

        newLesson.setTotalHour(prompt.inputInt(
            String.format("총수업시간? (%d) ", oldLesson.getTotalHour()),
            oldLesson.getTotalHour()));

        newLesson.setDailyHour(prompt.inputInt(String.format("일수업시간? (%d) ", oldLesson.getDailyHour()),
            oldLesson.getDailyHour()));

        if (newLesson.equals(oldLesson)) {
          System.out.println("수업정보 변경을 취소했습니다.");
        } else {

          out.writeUTF("/lesson/update");
          out.writeObject(newLesson);
          out.flush();

          String reply = in.readUTF();
          if(reply.equalsIgnoreCase("OK")) {
            System.out.println("수업 변경을 완료하였습니다.");
          } else if(reply.equalsIgnoreCase("FAIL")) {
            System.out.println(in.readUTF());
          }
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

}
