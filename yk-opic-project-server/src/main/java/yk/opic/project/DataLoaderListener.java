package yk.opic.project;

import java.util.HashMap;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.mariadb.BoardDaoImpl;
import yk.opic.project.mariadb.LessonDaoImpl;
import yk.opic.project.mariadb.MemberDaoImpl;
import yk.opic.project.mariadb.PhotoBoardDaoImpl;
import yk.opic.project.mariadb.PhotoFileDaoImpl;
import yk.opic.project.sql.DataSource;
import yk.opic.project.sql.PlatformTransactionManager;
import yk.opic.project.sql.TransactionTemplate;

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

    DataSource dataSource = new DataSource(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");

    PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
    context.put("dataSource", dataSource);
    context.put("transactionTemplate", new TransactionTemplate(txManager));
    context.put("boardDao", new BoardDaoImpl(dataSource));
    context.put("lessonDao", new LessonDaoImpl(dataSource));
    context.put("memberDao", new MemberDaoImpl(dataSource));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
    context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));

  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");

  }






}
