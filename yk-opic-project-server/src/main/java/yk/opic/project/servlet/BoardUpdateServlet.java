package yk.opic.project.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;
import yk.opic.project.util.Prompt;

public class BoardUpdateServlet implements Servlet {
  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      Board oldBoard = boardDao.findByNo(Prompt.inputInt(in, out, "번호? "));

      Board newBoard = new Board();

      newBoard.setNo(oldBoard.getNo());

      newBoard.setTitle(
          Prompt.inputString(in, out,
              String.format("제목? (%s) ", oldBoard.getTitle()),
              oldBoard.getTitle()));

      newBoard.setDate(new Date(System.currentTimeMillis()));

      newBoard.setViewCount(0);

      if (newBoard.equals(oldBoard)) {
        out.println("게시글 변경을 취소했습니다.");
        out.flush();
      } else {
        int index = boardDao.update(newBoard);

        if(index > 0) {
          out.println("게시글을 변경했습니다.");
        } else {
          out.println("게시글 변경을 취소했습니다.");
        }
        out.flush();
      }

    } catch(Exception e) {
      out.println("게시글 변경 중 오류발생!");
      out.flush();
      e.printStackTrace();
    }


  }

}
