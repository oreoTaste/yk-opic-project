package yk.opic.project.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.insert("PhotoBoardMapper.insert");
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAll() throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectList("PhotoBoardMapper.findAll");
    }
  }

  @Override
  public PhotoBoard findByNo(int photoNo) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectOne("PhotoBoardMapper.findByNo", photoNo);
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.update("PhotoBoardMapper.update");
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.delete("PhotoBoardMapper.delete");
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByLessonNo(int lessonNo) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectOne("PhotoBoardMapper.findAllByLessonNo", lessonNo);
    }
  }





}
