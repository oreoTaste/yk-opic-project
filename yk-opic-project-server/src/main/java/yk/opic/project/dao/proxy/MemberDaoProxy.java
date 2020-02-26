package yk.opic.project.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;

public class MemberDaoProxy implements MemberDao {

  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberDaoProxy(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public int insert(Member member) throws Exception {
    out.writeUTF("/member/add");
    out.writeObject(member);
    out.flush();

    String response = in.readUTF();
    if(response.equalsIgnoreCase("OK")) {
    } else if(response.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public Member findByNo(int no) throws Exception {
    out.writeUTF("/member/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if(response.equalsIgnoreCase("OK")) {
      return (Member) in.readObject();
    } else if(response.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    out.writeUTF("/member/list");
    out.flush();

    String response = in.readUTF();

    if(response.equalsIgnoreCase("OK")) {
      return (List<Member>) in.readObject();
    } else if(response.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return null;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/member/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if(response.equalsIgnoreCase("OK")) {
    } else if(response.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int update(Member member) throws Exception {
    out.writeUTF("/member/update");
    out.writeObject(member);
    out.flush();

    String reply = in.readUTF();
    if(reply.equalsIgnoreCase("OK")) {
      return 1;
    } else if(reply.equalsIgnoreCase("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 0;
  }

}
