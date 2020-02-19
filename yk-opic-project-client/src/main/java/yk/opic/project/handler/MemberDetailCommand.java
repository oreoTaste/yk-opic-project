package yk.opic.project.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.domain.Member;
import yk.opic.project.util.Prompt;

public class MemberDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public MemberDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }


  ////////////////////////////////////////////////////////////////

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        Member member = (Member) in.readObject();

        System.out.printf("번호? %d\n", member.getNo());
        System.out.printf("이름: %s\n", member.getName());
        System.out.printf("이메일: %s\n", member.getEmail());
        System.out.printf("비밀번호: %s\n", member.getPassword());
        System.out.printf("사진: %s\n", member.getPhoto());
        System.out.printf("전화: %s\n", member.getTel());
        System.out.printf("가입일: %1$tF %1$tH:%1$tM:%1$tS\n", member.getRegisteredDate());
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

}
