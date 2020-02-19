package yk.opic.project.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.domain.Member;

public class MemberListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {

    try {
      out.writeUTF("/member/list");
      out.flush();

      String response = in.readUTF();

      if(response.equalsIgnoreCase("OK")) {
        List<Member> member = (List<Member>) in.readObject();

        for (Member m : member) {

          System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tF %5$tH:%5$tM:%5$tS\n",
              m.getNo(),
              m.getName(),
              m.getEmail(),
              m.getTel(),
              m.getRegisteredDate());

        }
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

}
