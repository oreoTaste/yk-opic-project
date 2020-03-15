package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.Member;
import yk.opic.service.MemberService;
import yk.opic.util.Prompt;

public class MemberUpdateServlet implements Servlet {
  MemberService memberService;

  public MemberUpdateServlet(MemberService memberService) {
    this.memberService = memberService;
  }
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      Member oldMember = memberService.get(Prompt.inputInt(in, out, "번호? "));

      Member newMember = new Member();

      newMember.setNo(oldMember.getNo());

      newMember.setName(Prompt.inputString(in, out,
          String.format("이름? (%s) ", oldMember.getName()),
          oldMember.getName()));

      newMember.setEmail(Prompt.inputString(in, out,
          String.format("이메일? (%s) ", oldMember.getEmail()),
          oldMember.getEmail()));

      newMember.setPassword(Prompt.inputString(in, out,
          String.format("비밀번호? (%s) ", oldMember.getPassword()),
          oldMember.getPassword()));

      newMember.setPhoto(Prompt.inputString(in, out,
          String.format("사진? (%s) ", oldMember.getPhoto()),
          oldMember.getPhoto()));

      newMember.setTel(Prompt.inputString(in, out,
          String.format("전화? (%s) ", oldMember.getTel()),
          oldMember.getTel()));

      if (newMember.equals(oldMember)) {
        out.println("멤버 변경을 취소했습니다.");
      } else {

        if(memberService.update(newMember) > 0)
          out.println("멤버 변경을 완료하였습니다.");
        else {
          out.println("해당 번호의 멤버정보가 없습니다.");
        }
      }
    } catch(Exception e) {
      out.println("멤버 변경 실패");
      e.printStackTrace();
    }
  }

}
