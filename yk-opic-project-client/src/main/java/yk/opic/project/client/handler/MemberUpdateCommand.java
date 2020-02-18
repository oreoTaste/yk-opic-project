package yk.opic.project.client.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import yk.opic.project.client.domain.Member;
import yk.opic.project.client.util.Prompt;

public class MemberUpdateCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public MemberUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }



  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      out.writeUTF("/lesson/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      } else if(response.equalsIgnoreCase("OK")) {

        Member oldMember = (Member) in.readObject();
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
          System.out.println("멤버 변경을 취소했습니다.");
        } else {

          out.writeUTF("/member/update");
          out.writeObject(newMember);
          out.flush();

          String reply = in.readUTF();
          if(reply.equalsIgnoreCase("OK")) {
            System.out.println("멤버 변경을 완료하였습니다.");
          } else if(reply.equalsIgnoreCase("FAIL")) {
            System.out.println(in.readUTF());
          }
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

}
