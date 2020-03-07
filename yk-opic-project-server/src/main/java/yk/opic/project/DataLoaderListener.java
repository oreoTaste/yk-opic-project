package yk.opic.project;

import java.sql.SQLException;
import java.util.HashMap;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.mariadb.BoardDaoImpl;
import yk.opic.project.mariadb.LessonDaoImpl;
import yk.opic.project.mariadb.MemberDaoImpl;
import yk.opic.project.mariadb.PhotoBoardDaoImpl;
import yk.opic.project.mariadb.PhotoFileDaoImpl;
import yk.opic.project.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {

  String url;
  String username;
  String password;

  public DataLoaderListener() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");

      url = "jdbc:mariadb://localhost:3306/studydb";
      username = "study";
      password = "1111";

      ConnectionFactory conFactory = new ConnectionFactory(url, username, password);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    System.out.println("로딩시작");

    context.put("boardDao", new BoardDaoImpl(conFactory));
    context.put("lessonDao", new LessonDaoImpl(conFactory));
    context.put("memberDao", new MemberDaoImpl(conFactory));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
    context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));

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
