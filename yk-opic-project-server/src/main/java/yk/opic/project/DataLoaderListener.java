package yk.opic.project;

import java.util.HashMap;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.mariadb.BoardDaoImpl;
import yk.opic.project.mariadb.LessonDaoImpl;
import yk.opic.project.mariadb.MemberDaoImpl;
import yk.opic.project.mariadb.PhotoBoardDaoImpl;
import yk.opic.project.mariadb.PhotoFileDaoImpl;
import yk.opic.project.sql.ConnectionFactory;
import yk.opic.project.sql.PlatformTransactionManager;

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
    System.out.println("로딩시작");

    ConnectionFactory conFactory = new ConnectionFactory(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");

    context.put("connectionFactory", conFactory);
    context.put("platformTransactionManager", new PlatformTransactionManager(conFactory));
    context.put("boardDao", new BoardDaoImpl(conFactory));
    context.put("lessonDao", new LessonDaoImpl(conFactory));
    context.put("memberDao", new MemberDaoImpl(conFactory));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
    context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));

  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");

  }






}
