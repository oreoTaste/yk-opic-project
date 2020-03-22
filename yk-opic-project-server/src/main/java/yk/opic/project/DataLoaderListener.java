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
import yk.opic.project.service.impl.BoardServiceImpl2;
import yk.opic.project.service.impl.LessonServiceImpl;
import yk.opic.project.service.impl.MemberServiceImpl;
import yk.opic.project.service.impl.PhotoBoardServiceImpl;
import yk.opic.sql.MybatisDaoFactory;
import yk.opic.sql.PlatformTransactionManager;
import yk.opic.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    try {
      System.out.println("로딩시작");

      InputStream inputStream = Resources.getResourceAsStream(
          "yk/opic/project/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(inputStream));

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      BoardDao boardDao = daoFactory.createDao(BoardDao.class);
      LessonDao lessonDao = daoFactory.createDao(LessonDao.class);
      MemberDao memberDao = daoFactory.createDao(MemberDao.class);
      PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);
      PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);

      context.put("sqlSessionFactory", sqlSessionFactory);
      //      context.put("boardService", new BoardServiceImpl(boardDao));
      context.put("boardService", new BoardServiceImpl2(sqlSessionFactory));
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
