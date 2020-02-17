package yk.opic.project.client.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import yk.opic.project.client.domain.Board;
import yk.opic.project.client.util.Prompt;

public class BoardUpdateCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardUpdateCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if(response.equalsIgnoreCase("FAIL")) {
        System.out.println(in.readUTF());
      } else if(response.equalsIgnoreCase("OK")) {

        Board oldBoard = (Board) in.readObject();
        Board newBoard = new Board();

        newBoard.setNo(oldBoard.getNo());

        newBoard.setTitle(
            prompt.inputString(String.format("제목? (%s) ", oldBoard.getTitle()), oldBoard.getTitle()));

        newBoard.setDate(new Date(System.currentTimeMillis()));

        newBoard.setViewCount(0);

        if (newBoard.equals(oldBoard)) {
          System.out.println("게시글 변경을 취소했습니다.");
        } else {

          out.writeUTF("/board/update");
          out.writeObject(newBoard);
          out.flush();

          String reply = in.readUTF();
          if(reply.equalsIgnoreCase("OK")) {
            System.out.println("게시글 변경을 완료하였습니다.");
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
