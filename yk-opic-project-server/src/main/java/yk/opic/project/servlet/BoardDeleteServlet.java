package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.service.BoardService;
import yk.opic.util.Prompt;

public class BoardDeleteServlet implements Servlet {
  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int no = Prompt.inputInt(in, out, "번호? ");
      int index = boardService.delete(no);

      if(index > 0) {
        out.println("게시글을 삭제했습니다.");
      } else {
        out.println("해당 번호의 게시물이 없습니다.");
      }
      out.flush();
    } catch(Exception e) {
      out.println("게시물 삭제 중 오류발생!");
      out.flush();
      e.printStackTrace();
    }
  }

}
