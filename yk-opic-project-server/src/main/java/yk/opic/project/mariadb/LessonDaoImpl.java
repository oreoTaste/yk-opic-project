package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.sql.DataSource;

public class LessonDaoImpl implements LessonDao {

  DataSource dataSource;

  public LessonDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {

    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_lesson (conts, titl, sdt, edt, tot_hr, day_hr)"
                + " values(?, ?, ?, ?, ?, ?)")) {

      stmt.setString(1, lesson.getContext());
      stmt.setString(2, lesson.getTitle());
      stmt.setDate(3, lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHour());
      stmt.setInt(6, lesson.getDailyHour());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {

    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_lesson");
      List<Lesson> list = new ArrayList<>();
      while(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
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

    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_lesson where lesson_id = " + no);

      if(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
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

    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE lms_lesson SET"
                + " conts = ?,"
                + " titl = ?,"
                + " sdt = ?,"
                + " edt = ?,"
                + " tot_hr = ?,"
                + " day_hr = ?"
                + " WHERE lesson_id = ?")) {

      stmt.setString(1, lesson.getContext());
      stmt.setString(2, lesson.getTitle());
      stmt.setDate(3, lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHour());
      stmt.setInt(6, lesson.getDailyHour());
      stmt.setInt(7, lesson.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "DELETE from lms_lesson WHERE lesson_id = ?")) {

      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }





}
