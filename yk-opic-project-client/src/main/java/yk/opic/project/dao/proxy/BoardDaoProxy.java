package yk.opic.project.dao.proxy;

import java.util.List;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;

public class BoardDaoProxy implements BoardDao {

  DaoProxyHelper daoProxyHelper;

  public BoardDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Board board) throws Exception {

    return (int) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/board/add");
      out.reset();
      out.writeObject(board);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        return 1;
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return 0;
      }
      throw new Exception(in.readUTF());
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Board> findAll() throws Exception {
    return (List<Board>) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/board/list");
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        return (List<Board>) in.readObject();
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return null;
      }
      throw new Exception(in.readUTF());
    });
  }

  @Override
  public Board findByNo(int no) throws Exception {
    return (Board) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        return (Board) in.readObject();
      } else if(response.equalsIgnoreCase("FAIL")){
        System.out.println(in.readUTF());
        return null;
      }
      throw new Exception(in.readUTF());
    });
  }

  @Override
  public int update(Board board) throws Exception {
    return (int) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/board/update");
      out.writeObject(board);
      out.flush();

      String reply = in.readUTF();
      if(reply.equalsIgnoreCase("OK")) {
        return 1;
      } else if(reply.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return 0;
      }
      throw new Exception(in.readUTF());
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/board/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        return 1;
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return 0;
      }
      throw new Exception(in.readUTF());
    });
  }

}
