package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.domain.PhotoBoard;

public class PhotoBoardListServlet implements Servlet {
  PhotoBoardDao photoBoardDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      List<PhotoBoard> photoBoard = photoBoardDao.findAll();

      if(photoBoard != null) {
        for (PhotoBoard p : photoBoard) {

          out.printf("수업번호? %d\n수업명: %s\n",
              p.getLesson().getNo(),
              p.getLesson().getTitle());
          out.println("----------------------------------------------------");
          out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n",
              p.getNo(),
              p.getTitle(),
              p.getCreatedDate(),
              p.getViewCount());
        }
      } else {
        out.println("사진 게시물이 없습니다.");
      }
    } catch(Exception e) {
      out.println("사진 게시물 목록을 읽는중 오류발생!");
      out.flush();
      e.printStackTrace();
    }


  }

}
