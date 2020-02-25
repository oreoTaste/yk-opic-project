package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Board;

public class BoardListServlet implements Servlet {
  List<Board> boardList;

  public BoardListServlet(List<Board> boardList) {
    this.boardList = boardList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("OK");
        out.reset();
        out.writeObject(boardList);
  }

}
