package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Member;

public class MemberAddServlet implements Servlet {
  List<Member> memberList;

  public MemberAddServlet(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Member member = (Member) in.readObject();

      int index = 0;
      for(; index < memberList.size(); index++) {

        if(memberList.get(index).getNo() == member.getNo()) {
          break;
        }

      }

      if(index == memberList.size()) {
        memberList.add(member);
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.flush();
        out.writeUTF("같은 번호의 멤버 정보가 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

}
