package yk.opic.project.client.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.client.util.Prompt;

public class BoardDeleteCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardDeleteCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      out.writeUTF("/board/delete");

      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        System.out.println("게시글을 삭제했습니다.");
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


}
