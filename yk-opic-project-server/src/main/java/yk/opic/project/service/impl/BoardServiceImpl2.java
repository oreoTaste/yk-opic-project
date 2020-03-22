package yk.opic.project.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import yk.opic.project.domain.Board;
import yk.opic.project.service.BoardService;

public class BoardServiceImpl2 implements BoardService {
  SqlSessionFactory sqlSessionFactory;

  public BoardServiceImpl2(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int add(Board board) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.insert("yk.opic.project.dao.BoardDao.insert", board);
  }

  @Override
  public int delete(int boardNo) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.delete("yk.opic.project.dao.BoardDao.delete", boardNo);
  }

  @Override
  public Board get(int boardNo) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("yk.opic.project.dao.BoardDao.findByNo", boardNo);
  }

  @Override
  public int update(Board board) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.update("yk.opic.project.dao.BoardDao.update", board);
  }

  @Override
  public List<Board> list() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("yk.opic.project.dao.BoardDao.findAll");
  }

}
