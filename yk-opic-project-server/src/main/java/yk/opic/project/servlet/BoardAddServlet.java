package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;

public class BoardAddServlet implements Servlet {
  BoardDao boardDao;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    try {
      Board board = (Board) in.readObject();
      int index = boardDao.insert(board);

      if(index > 0) {
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 게시물이 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

}
