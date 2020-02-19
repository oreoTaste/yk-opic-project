package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Board;

public class BoardDeleteServlet implements Servlet {
  List<Board> boardList;

  public BoardDeleteServlet(List<Board> boardList) {
    this.boardList = boardList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = 0;
    for(; index < boardList.size(); index++) {

      if(boardList.get(index).getNo() == no) {
        break;
      }
    }

    if(index == boardList.size()) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      boardList.remove(index);
      out.writeUTF("OK");
      out.flush();
    }
  }

}
