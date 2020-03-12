package yk.opic.project.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;

public class LessonDaoImpl implements LessonDao {
  SqlSessionFactory sqlSessionFactory;
  public LessonDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.update("LessonMapper.insert", lesson);
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectList("LessonMapper.findAll");
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectOne("LessonMapper.findByNo", no);
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.update("LessonMapper.update", lesson);
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.delete("LessonMapper.delete", no);
      sqlSession.commit();
      return result;
    }
  }





}
