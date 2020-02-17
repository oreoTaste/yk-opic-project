package yk.opic.project.client.handler;

import java.util.List;
import yk.opic.project.client.domain.Lesson;
import yk.opic.util.Prompt;

public class LessonDetailCommand implements Command {
  List<Lesson> lessonList;
  Prompt prompt;

  public LessonDetailCommand(Prompt prompt, List<Lesson> list) {
    this.prompt = prompt;
    lessonList = list;
  }


  @Override
  public void execute() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfLesson(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
    }

    try {
      Lesson lesson = lessonList.get(index);
      System.out.printf("번호? %d\n", lesson.getNo());
      System.out.printf("수업명: %s\n", lesson.getTitle());
      System.out.printf("수업내용: %s\n", lesson.getContext());
      System.out.printf("기간 : %tF ~ %tF\n", lesson.getStartDate(), lesson.getEndDate());
      System.out.printf("총수업시간: %d\n", lesson.getTotalHour());
      System.out.printf("일수업시간: %d\n", lesson.getDailyHour());
    } catch (Exception e) {
      return;
    }
  }


  public int indexOfLesson(int no) {
    for (int i = 0; i < lessonList.size(); i++) {
      if (lessonList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }


}
