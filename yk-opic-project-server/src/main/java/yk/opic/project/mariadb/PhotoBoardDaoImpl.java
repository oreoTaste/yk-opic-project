package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.sql.ConnectionFactory;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  ConnectionFactory conFactory;

  public PhotoBoardDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {

    try(Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_photo (titl, cdt, vw_cnt, lesson_id)"
                + " values(?, now(), 0, ?)", Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, photoBoard.getTitle());
      stmt.setInt(2, photoBoard.getLesson().getNo());
      int result = stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if(rs.next()) {
        photoBoard.setNo(rs.getInt("photo_id"));
      }
      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAll() throws Exception {

    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery(
          "SELECT p.photo_id, l.lesson_id, l.titl, p.titl, p.cdt, p.vw_cnt"
              + " FROM lms_photo p left outer join lms_lesson l"
              + " on p.lesson_id = l.lesson_id");
      List<PhotoBoard> list = new ArrayList<>();
      while(rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("p.photo_id"));
        photoBoard.setTitle(rs.getString("p.titl"));
        photoBoard.setCreatedDate(rs.getDate("p.cdt"));
        photoBoard.setViewCount(rs.getInt("p.vw_cnt"));

        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("l.lesson_id"));
        lesson.setTitle(rs.getString("l.titl"));
        photoBoard.setLesson(lesson);
        list.add(photoBoard);
      }
      return list;
    }
  }

  @Override
  public PhotoBoard findByNo(int photoNo) throws Exception {

    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery(
          "SELECT * FROM lms_photo p left outer join lms_lesson l"
              + " on p.lesson_id = l.lesson_id"
              + " where p.photo_id = " + photoNo);

      while(rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("p.photo_id"));
        photoBoard.setTitle(rs.getString("p.titl"));
        photoBoard.setCreatedDate(rs.getDate("p.cdt"));
        photoBoard.setViewCount(rs.getInt("p.vw_cnt"));

        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("l.lesson_id"));
        lesson.setTitle(rs.getString("l.titl"));
        photoBoard.setLesson(lesson);
        return photoBoard;
      }
    }
    return null;
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {

    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(
          "UPDATE lms_photo SET"
              + " titl = '" + photoBoard.getTitle()
              + "', cdt = now()"
              + ", vw_cnt = 0"
              + ", lesson_id = " + photoBoard.getLesson().getNo()
              + " WHERE photo_id = " + photoBoard.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(
          "DELETE from lms_photo WHERE photo_id = " + no);
    }
  }

  @Override
  public List<PhotoBoard> findAllByLessonNo(int lessonNo) throws Exception {
    try(Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT * from lms_photo p left outer join lms_lesson l"
                + " on p.lesson_id = l.lesson_id"
                + " WHERE p.lesson_id = ?")) {

      stmt.setInt(1, lessonNo);

      ResultSet rs = stmt.executeQuery();

      List<PhotoBoard> list = new ArrayList<>();
      while(rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("p.photo_id"));
        photoBoard.setTitle(rs.getString("p.titl"));
        photoBoard.setCreatedDate(rs.getDate("p.cdt"));
        photoBoard.setViewCount(rs.getInt("p.vw_cnt"));

        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("l.lesson_id"));
        lesson.setTitle(rs.getString("l.titl"));
        photoBoard.setLesson(lesson);
        list.add(photoBoard);
      }
      return list;
    }
  }





}
