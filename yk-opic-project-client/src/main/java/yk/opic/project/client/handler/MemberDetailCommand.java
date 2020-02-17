package yk.opic.project.client.handler;

import java.util.List;
import yk.opic.project.client.domain.Member;
import yk.opic.util.Prompt;

public class MemberDetailCommand implements Command {
  List<Member> memberList;
  Prompt prompt;

  public MemberDetailCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
  }


  ////////////////////////////////////////////////////////////////

  @Override
  public void execute() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }

    Member member = memberList.get(index);

    try {
      System.out.printf("번호? %d\n", member.getNo());
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("비밀번호: %s\n", member.getPassword());
      System.out.printf("사진: %s\n", member.getPhoto());
      System.out.printf("전화: %s\n", member.getTel());
      System.out.printf("가입일: %1$tF %1$tH:%1$tM:%1$tS\n", member.getRegisteredDate());
    } catch (Exception e) {
      return;
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
