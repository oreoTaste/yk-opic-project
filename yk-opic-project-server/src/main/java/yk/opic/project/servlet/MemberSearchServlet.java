package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.domain.Member;
import yk.opic.service.MemberService;
import yk.opic.util.Prompt;

public class MemberSearchServlet implements Servlet {
  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      String word = Prompt.inputString(in, out, "검색어? ");
      List<Member> member = memberService.get(word);

      if(member != null) {
        for(Member m : member) {
          out.printf("%1$d, %2$s , %3$s       , %4$s,   %5$s      , %6$tF %6$tH:%6$tM:%6$tS\n",
              m.getNo(),
              m.getName(),
              m.getEmail(),
              m.getPhoto(),
              m.getTel(),
              m.getRegisteredDate());
        }
      } else {
        out.println("해당 검색어에 해당하는 멤버정보가 없습니다.");
      }
    } catch(Exception e) {
      out.println("멤버 조회 실패");
      e.printStackTrace();
    }
  }
}
