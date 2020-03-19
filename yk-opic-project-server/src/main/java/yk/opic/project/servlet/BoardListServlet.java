package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.domain.Board;
import yk.opic.project.service.BoardService;

public class BoardListServlet implements Servlet {
  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      List<Board> board = boardService.list();

      if(board != null) {
        for (Board b : board) {
          out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n",
              b.getNo(),
              b.getTitle(),
              b.getDate(),
              b.getViewCount());
        }
      } else {
        out.println("게시물이 없습니다.");
      }
    } catch(Exception e) {
      out.println("게시물 목록을 읽는중 오류발생!");
      out.flush();
      e.printStackTrace();
    }


  }

}
