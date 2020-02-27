package yk.opic.project.handler;

import java.sql.Date;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.project.util.Prompt;

public class MemberUpdateCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberUpdateCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      Member oldMember = memberDao.findByNo(prompt.inputInt("번호? "));

      Member newMember = new Member();

      newMember.setNo(oldMember.getNo());

      newMember.setName(prompt.inputString(
          String.format("이름? (%s) ", oldMember.getName()),
          oldMember.getName()));

      newMember.setEmail(prompt.inputString(
          String.format("이메일? (%s) ", oldMember.getEmail()),
          oldMember.getEmail()));

      newMember.setPassword(prompt.inputString(
          String.format("비밀번호? (%s) ", oldMember.getPassword()),
          oldMember.getPassword()));

      newMember.setPhoto(prompt.inputString(
          String.format("사진? (%s) ", oldMember.getPhoto()),
          oldMember.getPhoto()));

      newMember.setTel(prompt.inputString(
          String.format("전화? (%s) ", oldMember.getTel()),
          oldMember.getTel()));

      newMember.setRegisteredDate(new Date(System.currentTimeMillis()));

      if (newMember.equals(oldMember)) {
        System.out.println("멤버 변경을 취소했습니다.");
      } else {

        if(memberDao.update(newMember) > 0)
          System.out.println("멤버 변경을 완료하였습니다.");
        else {
          System.out.println("멤버 변경 실패");
        }
      }
    } catch(Exception e) {
    }
  }

}
