package yk.opic.service.impl;

import java.util.List;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;
import yk.opic.service.BoardService;

public class BoardServiceImpl implements BoardService{
  BoardDao boardDao;

  public BoardServiceImpl(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public int add(Board board) throws Exception {
    return boardDao.insert(board);
  }

  @Override
  public int delete(int boardNo) throws Exception {
    return boardDao.delete(boardNo);
  }

  @Override
  public Board get(int boardNo) throws Exception {
    return boardDao.findByNo(boardNo);
  }

  @Override
  public int update(Board board) throws Exception {
    return boardDao.update(board);
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.findAll();
  }

}
