package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  Connection con;

  public PhotoFileDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      return stmt.executeUpdate(
          "INSERT INTO lms_photo (titl, cdt, vw_cnt, lesson_id)"
              + " values('" + photoFile.getTitle()
              + "', '"  + photoFile.getCreatedDate()
              + "', '" + photoFile.getViewCount()
              + "', '" + photoFile.getLesson().getNo()
              +"')");
    }
  }

  @Override
  public List<PhotoFile> findAll(int photoNo) throws Exception {

    try(PreparedStatement stmt = con.prepareStatement(
        "SELECT photo_file_id, photo_id, filePath"
            + " FROM lms_photo_file"
            + " where photo_id = ?")) {

      stmt.setInt(1, photoNo);
      ResultSet rs = stmt.executeQuery();

      List<PhotoFile> list = new ArrayList<>();
      while(rs.next()) {

        list.add(new PhotoFile()
            .setNo(rs.getInt("f.photo_file_id"))
            .setPhotoNo(rs.getInt("f.photo_id"))
            .setFilePath(rs.getString("f.file_path")));
      }
      return list;
    }
  }

  @Override
  public PhotoFile findByNo(int no) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      ResultSet rs = stmt.executeQuery(
          "SELECT * FROM lms_photo p left outer join lms_lesson l"
              + " on p.lesson_id = l.lesson_id"
              + " where photo_id = " + no);

      if(rs.next()) {
        PhotoFile photoFile = new PhotoFile();
        photoFile.setNo(rs.getInt("p.photo_id"));
        photoFile.setTitle(rs.getString("p.titl"));
        photoFile.setCreatedDate(rs.getDate("p.cdt"));
        photoFile.setViewCount(rs.getInt("p.vw_cnt"));

        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("l.lesson_id"));
        lesson.setTitle(rs.getString("l.titl"));
        photoFile.setLesson(lesson);
        return photoFile;
      } else {
        return null;
      }
    }
  }

  @Override
  public int update(PhotoFile photoFile) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "UPDATE lms_photo SET"
              + " titl = '" + photoFile.getTitle()
              + "', cdt = now()"
              + ", vw_cnt = 0"
              + ", lesson_id = " + photoFile.getLesson().getNo()
              + " WHERE photo_id = " + photoFile.getNo());
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
