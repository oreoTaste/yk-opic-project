package yk.opic.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.mariadb.BoardDaoImpl;
import yk.opic.project.mariadb.LessonDaoImpl;
import yk.opic.project.mariadb.MemberDaoImpl;
import yk.opic.project.mariadb.PhotoBoardDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  Connection con;

  public DataLoaderListener() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    System.out.println("로딩시작");

    context.put("boardDao", new BoardDaoImpl(con));
    context.put("lessonDao", new LessonDaoImpl(con));
    context.put("memberDao", new MemberDaoImpl(con));
    context.put("boardPhotoDao", new PhotoBoardDaoImpl(con));
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");

    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }






}
