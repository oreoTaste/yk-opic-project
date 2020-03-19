package yk.opic.project.service;

import java.util.List;
import yk.opic.project.domain.Board;

public interface BoardService {

  int add(Board board) throws Exception;

  int delete(int boardNo) throws Exception;

  Board get(int boardNo) throws Exception;

  int update(Board board) throws Exception;

  List<Board> list() throws Exception;
}
