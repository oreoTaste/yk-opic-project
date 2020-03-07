package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.DataLoaderListener;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    DataLoaderListener.con.setAutoCommit(false);
    try {
      int no = Prompt.inputInt(in, out, "번호? ");

      photoFileDao.deleteAll(no);
      int index = photoBoardDao.delete(no);

      if(index > 0) {
        DataLoaderListener.con.commit();
        DataLoaderListener.con.setAutoCommit(true);
        out.println("사진 게시글을 삭제했습니다.");
      } else {
        DataLoaderListener.con.rollback();
        DataLoaderListener.con.setAutoCommit(true);
        out.println("해당 번호의 사진 게시물이 없습니다.");
      }
      out.flush();
    } catch(Exception e) {
      DataLoaderListener.con.rollback();
      DataLoaderListener.con.setAutoCommit(true);
      out.println("사진 게시글 삭제 중 오류발생!");
      out.flush();
      e.printStackTrace();
    }
  }

}
