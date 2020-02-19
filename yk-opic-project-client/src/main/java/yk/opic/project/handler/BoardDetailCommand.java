package yk.opic.project.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import yk.opic.project.domain.Board;
import yk.opic.project.util.Prompt;

public class BoardDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public BoardDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        Board board = (Board) in.readObject();

        System.out.printf("번호 : %d\n", board.getNo());
        System.out.printf("제목 : %s\n", board.getTitle());
        System.out.printf("등록일 : %1$tF %1$tH:%1$tM:%1$tS\n", board.getDate());
        System.out.printf("조회수 : %d\n", board.getViewCount());

      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch(Exception e) {
      e.printStackTrace();
    }

  }


}
