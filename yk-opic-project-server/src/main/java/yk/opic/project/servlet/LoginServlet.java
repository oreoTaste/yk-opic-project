package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.project.util.Prompt;

public class LoginServlet implements Servlet {
  MemberDao memberDao;

  public LoginServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {

      String email = Prompt.inputString(in, out, "이메일? ");
      String password = Prompt.inputString(in, out, "암호? ");
      Member member = memberDao.findByEmailAndPassword(email, password);

      if(member != null) {
        out.printf("'%s'님 반갑습니다.\n", member.getName());
      } else {
        out.println("사용자 정보가 유효하지 않습니다.");
      }
    } catch(Exception e) {
      out.println("멤버 조회 실패");
      e.printStackTrace();
    }
  }
}
