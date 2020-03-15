package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.service.PhotoBoardService;
import yk.opic.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {
  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.inputInt(in, out, "번호? ");

    if(photoBoardService.delete(no) == 0) {
      out.println("해당 번호의 사진 게시물이 없습니다.");
    } else {
      out.println("사진 게시글을 삭제했습니다.");
    }
    out.flush();
  }
}

