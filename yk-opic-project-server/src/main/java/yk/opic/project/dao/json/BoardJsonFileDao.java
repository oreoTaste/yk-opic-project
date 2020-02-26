package yk.opic.project.dao.json;

import java.util.List;
import yk.opic.project.domain.Board;

public class BoardJsonFileDao extends AbstractJsonFileDao<Board> {

  public BoardJsonFileDao(String fileName) {
    super(fileName);
    loadData();
  }


  public int insert(Board board) throws Exception {
    int index = indexOf(board.getNo());

    if(index >= 0)
      return 0;

    list.add(board);
    saveData();
    return 1;
  }

  public Board findByNo(int no) throws Exception {

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return null;
    }
    return list.get(index);
  }

  public List<Board> findAll() throws Exception {
    return list;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return 0;
    }

    list.remove(index);
    saveData();
    System.out.println("게시글을 삭제했습니다.");
    return 1;
  }

  public int update(Board board) throws Exception {

    int index = indexOf(board.getNo());

    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return 0;
    }

    Board oldBoard = list.get(index);

    if (board.equals(oldBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
      return 0;
    } else {
      list.set(index, board);
      saveData();
      System.out.println("게시글을 변경했습니다.");
      return 1;
    }
  }

  @Override
  protected int indexOf(int no) throws Exception {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no)
        return i;
    }
    return -1;
  }

}
