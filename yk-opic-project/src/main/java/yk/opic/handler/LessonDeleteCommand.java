package yk.opic.handler;

import java.util.List;
import yk.opic.domain.Lesson;
import yk.opic.util.Prompt;

public class LessonDeleteCommand implements Command {
  List<Lesson> lessonList;
  Prompt prompt;

  public LessonDeleteCommand(Prompt prompt, List<Lesson> list) {
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

    lessonList.remove(index);
    System.out.println("수업을 삭제했습니다.");
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
