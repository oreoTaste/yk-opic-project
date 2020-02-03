package yk.opic.handler;

import java.util.List;
import yk.opic.domain.Board;

public class BoardListCommand implements Command {
  List<Board> boardList;

  public BoardListCommand(List<Board> list) {
    boardList = list;
  }

  @Override
  public void execute() {
    Board[] board = new Board[boardList.size()];
    boardList.toArray(board);
    for (Board b : board) {
      System.out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n", b.getNo(), b.getTitle(),
          b.getDate(), b.getViewCount());
    }
  }



}
