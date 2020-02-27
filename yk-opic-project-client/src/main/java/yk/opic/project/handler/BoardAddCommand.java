package yk.opic.project.handler;

import java.sql.Date;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;
import yk.opic.project.util.Prompt;

public class BoardAddCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardAddCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      Board board = new Board();

      board.setNo(prompt.inputInt("번호? "));
      board.setTitle(prompt.inputString("내용? "));
      board.setDate(new Date(System.currentTimeMillis()));
      board.setViewCount(0);

      int index = boardDao.insert(board);

      if(index > 0) {
        System.out.println("게시글을 등록하였습니다.");
      } else
        System.out.println("해당 게시글이 이미 존재합니다.");

    } catch (Exception e) {
      System.out.println("게시물 등록 중 오류발생");
      e.printStackTrace();
    }


  }

}
