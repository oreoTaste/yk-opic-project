package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.json.BoardJsonFileDao;
import yk.opic.project.domain.Board;

public class BoardDetailServlet implements Servlet {
  BoardJsonFileDao boardDao;

  public BoardDetailServlet(BoardJsonFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    try {

      int no = in.readInt();
      Board board = boardDao.findByNo(no);

      if(board == null) {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
        out.flush();
      } else {
        out.writeUTF("OK");
        out.writeObject(board);
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    }

  }

}
