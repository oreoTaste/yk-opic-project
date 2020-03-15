package yk.opic.project.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {
  SqlSessionFactory sqlSessionFactory;

  public PhotoFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }



  @Override
  public List<PhotoFile> findAll(int photoNo) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectList("PhotoFileMapper.findAll",photoNo);
    }
  }



  @Override
  public int deleteAll(int photoNo) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.delete("PhotoFileMapper.deleteAll",photoNo);
      return result;
    }
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.insert("PhotoFileMapper.insert",photoBoard);
      return result;
    }
  }







}
