package yk.opic.project.client.handler;

import java.sql.Date;
import java.util.List;
import yk.opic.project.client.domain.Member;
import yk.opic.util.Prompt;

public class MemberAddCommand implements Command {
  List<Member> memberList;
  Prompt prompt;

  public MemberAddCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
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

    memberList.add(member);
  }


}
