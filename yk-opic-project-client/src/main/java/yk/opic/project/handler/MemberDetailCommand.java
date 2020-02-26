package yk.opic.project.handler;

import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.project.util.Prompt;

public class MemberDetailCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberDetailCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }


  ////////////////////////////////////////////////////////////////

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      Member member = memberDao.findByNo(no);

      if(member != null) {
        System.out.printf("번호? %d\n", member.getNo());
        System.out.printf("이름: %s\n", member.getName());
        System.out.printf("이메일: %s\n", member.getEmail());
        System.out.printf("비밀번호: %s\n", member.getPassword());
        System.out.printf("사진: %s\n", member.getPhoto());
        System.out.printf("전화: %s\n", member.getTel());
        System.out.printf("가입일: %1$tF %1$tH:%1$tM:%1$tS\n", member.getRegisteredDate());
      }
    } catch(Exception e) {
      System.out.println("멤버 조회 실패");
      e.printStackTrace();
    }
  }

}
