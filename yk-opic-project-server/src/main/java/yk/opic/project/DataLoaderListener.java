package yk.opic.project;

import java.io.InputStream;
import java.util.HashMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.mariadb.BoardDaoImpl;
import yk.opic.project.mariadb.LessonDaoImpl;
import yk.opic.project.mariadb.MemberDaoImpl;
import yk.opic.project.mariadb.PhotoBoardDaoImpl;
import yk.opic.project.mariadb.PhotoFileDaoImpl;
import yk.opic.service.impl.BoardServiceImpl;
import yk.opic.service.impl.LessonServiceImpl;
import yk.opic.service.impl.MemberServiceImpl;
import yk.opic.service.impl.PhotoBoardServiceImpl;
import yk.opic.sql.PlatformTransactionManager;
import yk.opic.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {

  public DataLoaderListener() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    try {
      System.out.println("로딩시작");

      InputStream inputStream = Resources.getResourceAsStream(
          "yk/opic/project/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(inputStream));

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);

      BoardDao boardDao = new BoardDaoImpl(sqlSessionFactory);
      LessonDao lessonDao = new LessonDaoImpl(sqlSessionFactory);
      MemberDao memberDao = new MemberDaoImpl(sqlSessionFactory);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(sqlSessionFactory);
      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(sqlSessionFactory);

      context.put("sqlSessionFactory", sqlSessionFactory);
      context.put("boardService", new BoardServiceImpl(boardDao));
      context.put("lessonService", new LessonServiceImpl(lessonDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(photoBoardDao, photoFileDao, txManager));
    } catch(Exception e) {

    }
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");
  }






}
