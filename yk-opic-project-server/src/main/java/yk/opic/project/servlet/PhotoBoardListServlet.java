package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.util.Prompt;

public class PhotoBoardListServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  LessonDao lessonDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, LessonDao lessonDao) {
    this.photoBoardDao = photoBoardDao;
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int lessonNo = Prompt.inputInt(in, out, "수업번호?");
      Lesson lesson = lessonDao.findByNo(lessonNo);

      if(lesson == null) {
        out.println("해당 수업번호가 유효하지 않습니다.");
        return;
      } else {

        out.printf("수업명: %s\n",
            lesson.getTitle());
        out.println("----------------------------------------------------");

        List<PhotoBoard> photoBoard = photoBoardDao.findAllByLessonNo(lessonNo);
        if(photoBoard != null) {
          for (PhotoBoard p : photoBoard) {
            out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n",
                p.getNo(),
                p.getTitle(),
                p.getCreatedDate(),
                p.getViewCount());
          }
        } else {
          out.println("사진 게시물이 없습니다.");
        }
      }
    } catch(Exception e) {
      out.println("사진 게시물 목록을 읽는중 오류발생!");
      out.flush();
      e.printStackTrace();
    }


  }

}
