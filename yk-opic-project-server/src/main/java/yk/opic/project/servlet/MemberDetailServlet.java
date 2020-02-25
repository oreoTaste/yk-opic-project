package yk.opic.project.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Member;

public class MemberDetailServlet implements Servlet {
  List<Member> memberList;

  public MemberDetailServlet(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = 0;
    for(; index < memberList.size(); index++) {

      if(memberList.get(index).getNo() == no) {
        break;
      }

    }

    if(index == memberList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 멤버정보가 없습니다.");
      out.flush();
    } else {
      out.writeUTF("OK");
      out.writeObject(memberList.get(index));
      out.flush();
    }
  }

}
