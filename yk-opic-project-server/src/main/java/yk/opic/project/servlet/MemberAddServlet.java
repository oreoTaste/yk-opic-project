package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.Member;
import yk.opic.project.service.MemberService;
import yk.opic.util.Prompt;

public class MemberAddServlet implements Servlet {
  MemberService memberService;

  public MemberAddServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Member member = new Member();

    member.setName(Prompt.inputString(in, out, "이름? "));
    member.setEmail(Prompt.inputString(in, out, "이메일? "));
    member.setPassword(Prompt.inputString(in, out, "비밀번호? "));
    member.setPhoto(Prompt.inputString(in, out, "사진? "));
    member.setTel(Prompt.inputString(in, out, "전화? "));

    try {
      int index = memberService.add(member);
      if(index > 0) {
        out.println("멤버 정보를 추가하였습니다.");
      } else
        out.println("같은 멤버가 존재합니다.");
    } catch (Exception e) {
      out.println("멤버 입력중 오류발생");
    }
  }
}
