package yk.opic.project;

import java.io.InputStream;
import java.util.HashMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.mariadb.BoardDaoImpl;
import yk.opic.project.mariadb.LessonDaoImpl;
import yk.opic.project.mariadb.MemberDaoImpl;
import yk.opic.project.mariadb.PhotoBoardDaoImpl;
import yk.opic.project.mariadb.PhotoFileDaoImpl;
import yk.opic.sql.DataSource;
import yk.opic.sql.PlatformTransactionManager;
import yk.opic.sql.TransactionTemplate;

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

      DataSource dataSource = new DataSource(
          "jdbc:mariadb://localhost:3306/studydb", "study", "1111");

      InputStream inputStream = Resources.getResourceAsStream(
          "yk/opic/project/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
      context.put("dataSource", dataSource);
      context.put("transactionTemplate", new TransactionTemplate(txManager));
      context.put("boardDao", new BoardDaoImpl(sqlSessionFactory));
      context.put("lessonDao", new LessonDaoImpl(sqlSessionFactory));
      context.put("memberDao", new MemberDaoImpl(sqlSessionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(sqlSessionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));
    } catch(Exception e) {

    }
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");
    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }






}
