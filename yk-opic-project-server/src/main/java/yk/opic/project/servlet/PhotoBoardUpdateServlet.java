package yk.opic.project.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      PhotoBoard oldBoard = photoBoardDao.findByNo(Prompt.inputInt(in, out, "번호? "));

      PhotoBoard newBoard = new PhotoBoard();

      newBoard.setTitle(
          Prompt.inputString(in, out,
              String.format("제목? (%s)", oldBoard.getTitle()),
              oldBoard.getTitle()));
      newBoard.setCreatedDate(new Date(System.currentTimeMillis()));
      newBoard.setViewCount(0);

      Lesson lesson = new Lesson();
      lesson.setNo(Prompt.inputInt(in, out, "수업번호? "));
      newBoard.setLesson(lesson);

      if (newBoard.equals(oldBoard)) {
        out.println("해당 번호의 사진 게시글이 없습니다.");
        out.flush();
      } else {
        int index = photoBoardDao.update(newBoard);

        if(index > 0) {
          out.println("사진을 변경했습니다.");
        } else {
          out.println("해당 번호의 사진 게시글이 없습니다.");
        }
        out.flush();
      }

    } catch(Exception e) {
      out.println("사진 게시글 변경 중 오류발생!");
      out.flush();
      e.printStackTrace();
    }


  }

}
