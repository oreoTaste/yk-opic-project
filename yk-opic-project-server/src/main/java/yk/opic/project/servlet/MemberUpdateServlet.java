package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.MemberObjectFileDao;
import yk.opic.project.domain.Member;

public class MemberUpdateServlet implements Servlet {
  MemberObjectFileDao memberDao;

  public MemberUpdateServlet(MemberObjectFileDao memberDao) {
    this.memberDao = memberDao;
  }
  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Member member = new Member();
    member = (Member) in.readObject();

    int index = memberDao.update(member);

    if(index == 0) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 멤버정보가 없습니다.");
      out.flush();
    } else {
      out.writeUTF("OK");
      out.flush();
    }
  }

}
