package yk.opic.handler;

import java.sql.Date;
import java.util.List;
import yk.opic.domain.Member;
import yk.opic.util.Prompt;

public class MemberUpdateCommand implements Command {
  List<Member> memberList;
  Prompt prompt;

  public MemberUpdateCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
  }


  @Override
  public void execute() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }

    Member oldMember = memberList.get(index);
    Member newMember = new Member();

    newMember.setNo(oldMember.getNo());

    newMember.setName(
        prompt.inputString(String.format("이름? (%s) ", oldMember.getName()), oldMember.getName()));

    newMember.setEmail(prompt.inputString(String.format("이메일? (%s) ", oldMember.getEmail()),
        oldMember.getEmail()));

    newMember.setPassword(prompt.inputString(String.format("비밀번호? (%s) ", oldMember.getPassword()),
        oldMember.getPassword()));

    newMember.setPhoto(
        prompt.inputString(String.format("사진? (%s) ", oldMember.getPhoto()), oldMember.getPhoto()));

    newMember.setTel(
        prompt.inputString(String.format("전화? (%s) ", oldMember.getTel()), oldMember.getTel()));

    newMember.setRegisteredDate(new Date(System.currentTimeMillis()));

    if (newMember.equals(oldMember)) {
      System.out.println("회원 변경을 취소했습니다.");
    } else {
      memberList.set(index, newMember);
      System.out.println("회원을 변경했습니다.");
    }
  }



  public int indexOfMember(int no) {
    for (int i = 0; i < memberList.size(); i++) {
      if (memberList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
