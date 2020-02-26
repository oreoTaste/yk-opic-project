package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.dao.json.MemberJsonFileDao;

public class MemberDeleteServlet implements Servlet {
  MemberJsonFileDao memberDao;

  public MemberDeleteServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = memberDao.delete(no);

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
