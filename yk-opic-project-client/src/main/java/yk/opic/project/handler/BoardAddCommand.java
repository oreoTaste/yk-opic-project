package yk.opic.project.handler;

import java.sql.Date;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;
import yk.opic.project.util.Prompt;

public class BoardAddCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardAddCommand(BoardDao boardDao, Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    try {
      boardDao.insert(board);
    } catch (Exception e) {
      e.getMessage();
    }

  }

}
