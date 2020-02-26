package yk.opic.project.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import yk.opic.project.domain.Board;

public class BoardObjectFileDao extends AbstractObjectFileDao<Board> implements BoardDao {

  public BoardObjectFileDao(String fileName) {
    super(fileName);
    loadData();
  }

  @Override
  @SuppressWarnings("unchecked")
  public void loadData() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      list = (LinkedList<Board>)in.readObject();

      System.out.printf("총 %d개 게시글 로딩하였습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  @Override
  public void saveData() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeObject(list);

      System.out.printf("총 %d개 게시글 저장하였습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }

  public int insert(Board board) throws Exception {
    int index = indexOf(board.getNo());

    if(index <= 0)
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
  public int indexOf(int no) throws Exception {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no)
        return i;
    }
    return -1;
  }

}
