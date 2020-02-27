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
      int index = boardDao.delete(no);

      if(index > 0) {
        System.out.println("게시글을 삭제했습니다.");
      } else {

      }

    } catch (Exception e) {
      System.out.println("게시글 삭제중 오류발생!");
      e.printStackTrace();
    }

  }


}
