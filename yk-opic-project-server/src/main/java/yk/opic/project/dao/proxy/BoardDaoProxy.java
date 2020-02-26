package yk.opic.project.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;

public class BoardDaoProxy implements BoardDao {

  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardDaoProxy(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public int insert(Board board) throws Exception {

    out.writeUTF("/board/add");
    out.writeObject(board);
    out.flush();

    String response = in.readUTF();
    if(response.equalsIgnoreCase("OK")) {
    } else if(response.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Board> findAll() throws Exception {
    out.writeUTF("/board/list");
    out.flush();

    String response = in.readUTF();
    if(response.equalsIgnoreCase("OK")) {
      return (List<Board>) in.readObject();
    } else if(response.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return null;
  }

  @Override
  public Board findByNo(int no) throws Exception {
    out.writeUTF("/board/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if(response.equalsIgnoreCase("OK")) {
      return (Board) in.readObject();

    } else {
      throw new Exception(in.readUTF());
    }
  }

  @Override
  public int update(Board board) throws Exception {
    out.writeUTF("/board/update");
    out.writeObject(board);
    out.flush();

    String reply = in.readUTF();
    if(reply.equalsIgnoreCase("OK")) {
      System.out.println("게시글 변경을 완료하였습니다.");
      return 1;
    } else if(reply.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 0;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/board/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if(response.equalsIgnoreCase("OK")) {
    } else if(response.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

}
