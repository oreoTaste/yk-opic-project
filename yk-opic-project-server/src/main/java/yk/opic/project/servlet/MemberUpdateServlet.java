package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Member;

public class MemberUpdateServlet implements Servlet {
  List<Member> memberList;

  public MemberUpdateServlet(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = new Member();
    member = (Member) in.readObject();

    int index = 0;
    for(; index < memberList.size(); index++) {

      if(memberList.get(index).getNo() == member.getNo()) {
        break;
      }

    }

    if(index == memberList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 멤버정보가 없습니다.");
      out.flush();
    } else {
      memberList.set(index, member);
      out.writeUTF("OK");
      out.flush();
    }
  }

}
