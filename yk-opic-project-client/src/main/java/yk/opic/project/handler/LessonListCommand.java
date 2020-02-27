package yk.opic.project.handler;

import java.util.List;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;

public class LessonListCommand implements Command {
  LessonDao lessonDao;

  public LessonListCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {

    try {
      List<Lesson> lesson = lessonDao.findAll();

      if(lesson != null) {
        for (Lesson ls : lesson) {
          System.out.printf("%d, %s     , %tF ~ %tF, %d\n",
              ls.getNo(),
              ls.getTitle(),
              ls.getStartDate(),
              ls.getEndDate(),
              ls.getTotalHour());
        }
      } else {
        System.out.println("수업정보가 없습니다.");
      }
    } catch(Exception e) {
      System.out.println("수업정보 불러오기 실패!");
      e.printStackTrace();
    }
  }


}
