package yk.opic.project.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;

public class BoardDaoImpl implements BoardDao {
  SqlSessionFactory sqlSessionFactory;

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Board board) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.insert("BoardMapper.insert", board);
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectList("BoardMapper.find");
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectOne("BoardMapper.find", no);
    }
  }

  @Override
  public int update(Board board) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.update("BoardMapper.update", board);
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.delete("BoardMapper.update", no);
      sqlSession.commit();
      return result;
    }
  }





}
