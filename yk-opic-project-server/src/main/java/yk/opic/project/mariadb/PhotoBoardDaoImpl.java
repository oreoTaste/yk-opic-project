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

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  Connection con;
  Lesson lesson;

  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {

    System.out.println("insert문");
    try(PreparedStatement stmt = con.prepareStatement(
        "INSERT INTO lms_photo (titl, cdt, vw_cnt, lesson_id)"
            + " values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

      con.setAutoCommit(true);

      System.out.println("title 입력전");
      stmt.setString(1, photoBoard.getTitle());
      System.out.println("title입력");
      stmt.setDate(2, photoBoard.getCreatedDate());
      System.out.println("cdt입력");
      stmt.setInt(3, photoBoard.getViewCount());
      System.out.println("vc입력");
      stmt.setInt(4, photoBoard.getLesson().getNo());
      System.out.println("lc입력");
      stmt.executeUpdate();
      System.out.println("수행입력");

      ResultSet rs = stmt.getGeneratedKeys();
      System.out.println("키생성");
      if(rs.next()) {
        System.out.println("rs 있는지보");
        return rs.getInt("photo_id");
      }
      return 0;
    }
  }

  @Override
  public List<PhotoBoard> findAll() throws Exception {

    try(Statement stmt = con.createStatement()) {

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
  public List<PhotoBoard> findByNo(int lessonNo) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      ResultSet rs = stmt.executeQuery(
          "SELECT * FROM lms_photo p left outer join lms_lesson l"
              + " on p.lesson_id = l.lesson_id"
              + " where p.lesson_id = " + lessonNo);

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
  public int update(PhotoBoard photoBoard) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
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

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "DELETE from lms_photo WHERE photo_id = " + no);
    }
  }





}
