package yk.opic.project.client.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import yk.opic.project.client.domain.Member;
import yk.opic.project.client.util.Prompt;

public class MemberAddCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public MemberAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
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
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        System.out.println("저장완료");
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
