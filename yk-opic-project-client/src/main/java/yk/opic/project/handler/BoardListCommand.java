package yk.opic.project.handler;

import java.util.List;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;

public class BoardListCommand implements Command {
  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {

    try {
      List<Board> board = boardDao.findAll();

      if(board != null) {
        for (Board b : board) {
          System.out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n",
              b.getNo(),
              b.getTitle(),
              b.getDate(),
              b.getViewCount());
        }
      } else {
        System.out.println("게시물이 없습니다.");
      }
    } catch(Exception e) {
      System.out.println("게시글 목록 불러오기 실패");
      e.printStackTrace();
    }

  }



}
