package yk.opic.project.handler;

import yk.opic.project.dao.BoardDao;
import yk.opic.project.util.Prompt;

public class BoardDeleteCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardDeleteCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      boardDao.delete(no);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }


}
