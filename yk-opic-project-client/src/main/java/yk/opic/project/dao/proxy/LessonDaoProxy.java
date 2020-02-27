package yk.opic.project.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;

public class LessonDaoProxy implements LessonDao {
  ObjectOutputStream out;
  ObjectInputStream in;

  public LessonDaoProxy(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
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
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {
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
  }

  @Override
  public int delete(int no) throws Exception {
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
  }

  @Override
  public int insert(Lesson lesson) throws Exception {

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
  }

  @Override
  public int update(Lesson lesson) throws Exception {
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
  }

}
