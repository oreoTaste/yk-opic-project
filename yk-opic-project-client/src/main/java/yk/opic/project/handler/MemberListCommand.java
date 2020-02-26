package yk.opic.project.handler;

import java.util.List;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;

public class MemberListCommand implements Command {
  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {

    try {
      List<Member> member = memberDao.findAll();
      if(member != null)
        for (Member m : member) {
          System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tF %5$tH:%5$tM:%5$tS\n",
              m.getNo(),
              m.getName(),
              m.getEmail(),
              m.getTel(),
              m.getRegisteredDate());
        }
    } catch(Exception e) {
      System.out.println("멤버 리스트 읽기 실패");
      e.printStackTrace();
    }
  }

}
