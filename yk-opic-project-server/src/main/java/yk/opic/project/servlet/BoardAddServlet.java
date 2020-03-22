package yk.opic.project.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import yk.opic.project.domain.Board;
import yk.opic.project.service.BoardService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/board/add")
public class BoardAddServlet implements Servlet {
  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      Board board = new Board();

      board.setTitle(Prompt.inputString(in, out, "내용? "));
      board.setDate(new Date(System.currentTimeMillis()));
      board.setViewCount(0);

      int index = boardService.add(board);

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
