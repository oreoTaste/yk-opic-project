package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.service.MemberService;
import yk.opic.util.Prompt;

public class MemberDeleteServlet implements Servlet {
  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int no = Prompt.inputInt(in, out, "번호? ");
      if(memberService.delete(no) > 0)
        out.println("멤버정보를 삭제했습니다.");
      else {
        out.println("해당 번호의 멤버정보가 없습니다.");
      }
    } catch (Exception e) {
      out.println("멤버정보 삭제 실패");
      e.printStackTrace();
    }
  }
}
