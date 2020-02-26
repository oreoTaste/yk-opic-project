package yk.opic.project.handler;

import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;
import yk.opic.project.util.Prompt;

public class BoardDetailCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardDetailCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {

      int no = prompt.inputInt("번호? ");
      Board board = boardDao.findByNo(no);

      System.out.printf("번호 : %d\n", board.getNo());
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("등록일 : %1$tF %1$tH:%1$tM:%1$tS\n", board.getDate());
      System.out.printf("조회수 : %d\n", board.getViewCount());

    } catch(Exception e) {
      System.out.println("조회실패!");
    }

  }


}
