package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.sql.PlatformTransactionManager;
import yk.opic.project.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  PlatformTransactionManager txManager;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao, PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    txManager.beginTransaction();
    try {
      int no = Prompt.inputInt(in, out, "번호? ");

      photoFileDao.deleteAll(no);
      int index = photoBoardDao.delete(no);

      if(index > 0) {
        txManager.commit();
        out.println("사진 게시글을 삭제했습니다.");
      } else {
        txManager.rollback();
        out.println("해당 번호의 사진 게시물이 없습니다.");
      }
      out.flush();
    } catch(Exception e) {
      txManager.rollback();
      out.println("사진 게시글 삭제 중 오류발생!");
      out.flush();
      e.printStackTrace();
    }
  }

}
