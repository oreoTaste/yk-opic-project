package yk.opic.project.client.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import yk.opic.project.client.domain.Board;
import yk.opic.project.client.util.Prompt;

public class BoardAddCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardAddCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    try {
      out.writeUTF("/board/add");
      out.writeObject(board);
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
