package yk.opic.project.handler;

import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.util.Prompt;

public class LessonDetailCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonDetailCommand(LessonDao lessonDao, Prompt prompt) {
    this.lessonDao = lessonDao;
    this.prompt = prompt;
  }


  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      Lesson lesson = lessonDao.findByNo(no);
      if(lesson != null) {
        System.out.printf("번호? %d\n", lesson.getNo());
        System.out.printf("수업명: %s\n", lesson.getTitle());
        System.out.printf("수업내용: %s\n", lesson.getContext());
        System.out.printf("기간 : %tF ~ %tF\n", lesson.getStartDate(), lesson.getEndDate());
        System.out.printf("총수업시간: %d\n", lesson.getTotalHour());
        System.out.printf("일수업시간: %d\n", lesson.getDailyHour());
      } else {
      }
    } catch(Exception e) {
      System.out.println("수업정보 조회중 오류발생!");
      e.printStackTrace();
    }
  }

}
