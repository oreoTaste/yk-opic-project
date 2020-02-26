package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;

public class MemberAddServlet implements Servlet {
  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Member member = (Member) in.readObject();

      int index = memberDao.insert(member);

      if(index > 0) {
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 멤버 정보가 있습니다.");
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
