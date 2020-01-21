package yk.opic.handler;

import java.util.List;
import yk.opic.domain.Member;

public class MemberListCommand implements Command {
  List<Member> memberList;

  public MemberListCommand(List<Member> list) {
    memberList = list;
  }

  @Override
  public void execute() {
    Member[] member = new Member[memberList.size()];
    memberList.toArray(member);
    for (Member m : member) {
      System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tF %5$tH:%5$tM:%5$tS\n",
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }

}
