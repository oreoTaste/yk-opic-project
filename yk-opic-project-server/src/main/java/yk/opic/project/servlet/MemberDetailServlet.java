package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.Member;
import yk.opic.project.service.MemberService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/member/detail")
public class MemberDetailServlet implements Servlet {
  MemberService memberService;

  public MemberDetailServlet(MemberService memberService) {
    this.memberService = memberService;
  }
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {

      int no = Prompt.inputInt(in, out, "번호? ");
      Member member = memberService.get(no);

      if(member != null) {
        out.printf("번호? %d\n", member.getNo());
        out.printf("이름: %s\n", member.getName());
        out.printf("이메일: %s\n", member.getEmail());
        out.printf("비밀번호: %s\n", member.getPassword());
        out.printf("사진: %s\n", member.getPhoto());
        out.printf("전화: %s\n", member.getTel());
        out.printf("가입일: %1$tF %1$tH:%1$tM:%1$tS\n", member.getRegisteredDate());
      } else {
        out.println("해당 번호의 멤버정보가 없습니다.");
      }
    } catch(Exception e) {
      out.println("멤버 조회 실패");
      e.printStackTrace();
    }
  }
}
