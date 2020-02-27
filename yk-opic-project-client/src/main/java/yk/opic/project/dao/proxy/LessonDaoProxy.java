package yk.opic.project.dao.proxy;

import java.util.List;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;

public class LessonDaoProxy implements LessonDao {
  DaoProxyHelper daoProxyHelper;

  public LessonDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public Lesson findByNo(int no) throws Exception {

    return (Lesson) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/lesson/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        return (Lesson) in.readObject();
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return null;
      }
      throw new Exception(in.readUTF());
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {
    return (List<Lesson>) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/lesson/list");
      out.flush();

      String response = in.readUTF();

      if(response.equalsIgnoreCase("OK")) {
        return (List<Lesson>) in.readObject();
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return null;
      }
      throw new Exception(in.readUTF());
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/lesson/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        out.writeUTF("게시글을 삭제했습니다.");
        return 1;
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return 0;
      }
      throw new Exception(in.readUTF());
    });
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    return (int) daoProxyHelper.execute((out, in) -> {

      out.writeUTF("/lesson/add");
      out.writeObject(lesson);
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

  @Override
  public int update(Lesson lesson) throws Exception {
    return (int) daoProxyHelper.execute((out, in) -> {
      out.writeUTF("/lesson/update");
      out.writeObject(lesson);
      out.flush();

      String reply = in.readUTF();
      if(reply.equalsIgnoreCase("OK")) {
        out.writeUTF("수업 변경을 완료하였습니다.");
        return 1;
      } else if(reply.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
        return 0;
      }
      throw new Exception(in.readUTF());
    });
  }

}
