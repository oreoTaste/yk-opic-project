package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Board;

public class BoardUpdateServlet implements Servlet {
  List<Board> boardList;

  public BoardUpdateServlet(List<Board> boardList) {
    this.boardList = boardList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = new Board();
    board = (Board) in.readObject();

    int index = 0;
    for(; index < boardList.size(); index++) {

      if(boardList.get(index).getNo() == board.getNo()) {
        break;
      }

    }

    if(index == boardList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      boardList.set(index, board);
      out.writeUTF("OK");
      out.flush();
    }
  }

}
