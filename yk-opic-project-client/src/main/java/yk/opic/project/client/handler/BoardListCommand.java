package yk.opic.project.client.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import yk.opic.project.client.domain.Board;

public class BoardListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {
      out.writeUTF("/board/list");
      out.flush();

      String response = in.readUTF();
      if(response.equalsIgnoreCase("OK")) {
        List<Board> board = (List<Board>) in.readObject();
        for (Board b : board) {
          System.out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n",
              b.getNo(),
              b.getTitle(),
              b.getDate(),
              b.getViewCount());
        }
      } else if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch(Exception e) {
      e.printStackTrace();
    }

  }



}
