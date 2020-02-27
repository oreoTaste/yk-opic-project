package yk.opic.project.handler;

import yk.opic.project.dao.LessonDao;
import yk.opic.project.util.Prompt;

public class LessonDeleteCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonDeleteCommand(LessonDao lessonDao, Prompt prompt) {
    this.lessonDao = lessonDao;
    this.prompt = prompt;
  }


  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      int index = lessonDao.delete(no);

      if(index > 0) {
        System.out.println("게시글을 삭제했습니다.");
      } else {
      }

    } catch (Exception e) {
      System.out.println("수업정보 삭제중 오류발생!");
      e.printStackTrace();
    }

  }


}
