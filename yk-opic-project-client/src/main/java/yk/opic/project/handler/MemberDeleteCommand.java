package yk.opic.project.handler;

import yk.opic.project.dao.MemberDao;
import yk.opic.project.util.Prompt;

public class MemberDeleteCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberDeleteCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      if(memberDao.delete(no) == 1)
        System.out.println("게시글을 삭제했습니다.");
    } catch (Exception e) {
      System.out.println("게시물 삭제 실패");
      e.printStackTrace();
    }
  }

}
