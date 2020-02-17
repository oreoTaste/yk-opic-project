package yk.opic.project.client.handler;

import java.util.List;
import yk.opic.project.client.domain.Member;
import yk.opic.util.Prompt;

public class MemberDeleteCommand implements Command {
  List<Member> memberList;
  Prompt prompt;

  public MemberDeleteCommand(Prompt prompt, List<Member> list) {
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

    memberList.remove(index);
    System.out.println("회원을 삭제했습니다.");
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
