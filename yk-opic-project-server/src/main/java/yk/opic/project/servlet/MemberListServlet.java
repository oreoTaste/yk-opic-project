package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;

public class MemberListServlet implements Servlet {
  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      List<Member> member = memberDao.findAll();
      if(member != null) {
        for (Member m : member) {
          out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tF %5$tH:%5$tM:%5$tS\n",
              m.getNo(),
              m.getName(),
              m.getEmail(),
              m.getTel(),
              m.getRegisteredDate());
        }
      } else {
        out.println("멤버리스트를 찾을 수 없습니다.");
      }
    } catch(Exception e) {
      out.println("멤버 리스트 중 오류발생!");
      e.printStackTrace();
    }
  }

}
