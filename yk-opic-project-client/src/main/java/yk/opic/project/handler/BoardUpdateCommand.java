package yk.opic.project.handler;

import java.sql.Date;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;
import yk.opic.project.util.Prompt;

public class BoardUpdateCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardUpdateCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      Board oldBoard = boardDao.findByNo(prompt.inputInt("번호? "));

      Board newBoard = new Board();

      newBoard.setNo(oldBoard.getNo());

      newBoard.setTitle(
          prompt.inputString(
              String.format("제목? (%s) ", oldBoard.getTitle()),
              oldBoard.getTitle()));

      newBoard.setDate(new Date(System.currentTimeMillis()));

      newBoard.setViewCount(0);

      if (newBoard.equals(oldBoard)) {
        System.out.println("게시글 변경을 취소했습니다.");
      } else {
        boardDao.update(newBoard);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }


  }
}
