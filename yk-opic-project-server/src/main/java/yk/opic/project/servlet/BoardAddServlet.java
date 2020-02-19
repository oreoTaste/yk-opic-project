package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Board;

public class BoardAddServlet implements Servlet {
  List<Board> boardList;

  public BoardAddServlet(List<Board> boardList) {
    this.boardList = boardList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Board board = (Board) in.readObject();

      int index = 0;
      for(; index < boardList.size(); index++) {

        if(boardList.get(index).getNo() == board.getNo()) {
          break;
        }

      }

      if(index == boardList.size()) {
        boardList.add(board);
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.flush();
        out.writeUTF("같은 번호의 게시물이 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

}
