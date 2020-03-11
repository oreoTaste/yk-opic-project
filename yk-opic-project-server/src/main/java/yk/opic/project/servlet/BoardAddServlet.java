package yk.opic.project.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;
import yk.opic.util.Prompt;

public class BoardAddServlet implements Servlet {
  BoardDao boardDao;
  Prompt prompt;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      Board board = new Board();

      board.setTitle(Prompt.inputString(in, out, "내용? "));
      board.setDate(new Date(System.currentTimeMillis()));
      board.setViewCount(0);

      int index = boardDao.insert(board);

      if(index > 0) {
        out.println("게시글을 등록하였습니다.");
        out.flush();
      } else {
        out.println("같은 번호의 게시물이 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.println("게시물 입력중 오류발생!");
      out.flush();
      e.printStackTrace();
    }
  }

}
