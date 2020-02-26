package yk.opic.project.handler;

import java.sql.Date;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.project.util.Prompt;

public class MemberAddCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberAddCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }
  @Override
  public void execute() {

    Member member = new Member();

    member.setNo(prompt.inputInt("번호?"));
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("비밀번호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      memberDao.insert(member);
    } catch (Exception e) {
      System.out.println("멤버 입력중 오류발생");
    }
  }


}
