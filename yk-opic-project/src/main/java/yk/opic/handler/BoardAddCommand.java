package yk.opic.handler;

import java.sql.Date;
import java.util.List;
import yk.opic.domain.Board;
import yk.opic.util.Prompt;

public class BoardAddCommand implements Command {
  List<Board> boardList;
  Prompt prompt;

  public BoardAddCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    boardList = list;
  }

  @Override
  public void execute() {
    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    boardList.add(board);
  }

}
