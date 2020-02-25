package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.BoardObjectFileDao;

public class BoardDeleteServlet implements Servlet {
  BoardObjectFileDao boardDao;

  public BoardDeleteServlet(BoardObjectFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    int index = boardDao.delete(no);

    if(index == 0) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      out.writeUTF("OK");
      out.flush();
    }
  }

}
