package yk.opic.handler;

import java.util.List;
import yk.opic.domain.Board;
import yk.opic.util.Prompt;

public class BoardDetailCommand implements Command {
  List<Board> boardList;
  Prompt prompt;

  public BoardDetailCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    boardList = list;
  }

  @Override
  public void execute() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfBoard(no);

    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    Board board = boardList.get(index);

    try {
      System.out.printf("번호 : %d\n", board.getNo());
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("등록일 : %1$tF %1$tH:%1$tM:%1$tS\n", board.getDate());
      System.out.printf("조회수 : %d\n", board.getViewCount());
    } catch (Exception e) {
      return;
    } finally {
    }
  }


  public int indexOfBoard(int no) {
    for (int i = 0; i < boardList.size(); i++) {
      if (boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
