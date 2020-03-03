package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;

public class LessonDaoImpl implements LessonDao {

  @Override
  public int insert(Lesson lesson) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      return stmt.executeUpdate("INSERT INTO lms_lesson (conts, titl, sdt, edt, tot_hr, day_hr)"
          + " values ("
          + "'"+lesson.getContext()+"', "
          + "'"+lesson.getTitle()+"', "
          + "'"+lesson.getStartDate()+"', "
          + "'"+lesson.getEndDate()+"', "
          + "'"+lesson.getTotalHour()+"', "
          + "'"+lesson.getDailyHour()+"'"
          + ");");
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "111");
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_lesson");
      List<Lesson> list = new ArrayList<>();
      while(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setContext(rs.getString("conts"));
        lesson.setTitle(rs.getString("titl"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHour(rs.getInt("tot_hr"));
        lesson.setDailyHour(rs.getInt("day_hr"));
        list.add(lesson);
      }
      return list;
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_lesson where lesson_id = " + no);

      if(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setContext(rs.getString("conts"));
        lesson.setTitle(rs.getString("titl"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHour(rs.getInt("tot_hr"));
        lesson.setDailyHour(rs.getInt("day_hr"));
        return lesson;
      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "UPDATE lms_lesson SET"
              + " conts = " + lesson.getContext()
              + " titl = " + lesson.getTitle()
              + " sdt = " + lesson.getStartDate()
              + " edt = " + lesson.getEndDate()
              + " tot_hr = " + lesson.getTotalHour()
              + " day_hr = " + lesson.getDailyHour()
              + " WHERE board_id = " + lesson.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "DELETE from lms_lesson WHERE lesson_id = " + no);
    }
  }





}
