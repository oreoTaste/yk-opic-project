package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.Board;
import yk.opic.project.service.BoardService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/board/detail")
public class BoardDetailServlet implements Servlet {
  BoardService boardService;

  public BoardDetailServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int no = Prompt.inputInt(in, out, "번호? ");
      Board board = boardService.get(no);

      if(board != null) {
        out.printf("번호 : %d\n", board.getNo());
        out.printf("제목 : %s\n", board.getTitle());
        out.printf("등록일 : %1$tF %1$tH:%1$tM:%1$tS\n", board.getDate());
        out.printf("조회수 : %d\n", board.getViewCount());
      } else {
        out.println("해당 번호의 게시글이 없습니다.");
      }
      out.flush();
    } catch(Exception e) {
      out.println("게시물 조회중 오류발생!");
      out.flush();
      e.printStackTrace();
    }

  }

}
