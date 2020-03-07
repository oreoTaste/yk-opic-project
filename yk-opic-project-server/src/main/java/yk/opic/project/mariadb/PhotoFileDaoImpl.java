package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  Connection con;

  public PhotoFileDaoImpl(Connection con) {
    this.con = con;
  }


  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into lms_photo_file(photo_id,file_path) values(?, ?)")) {

      con.setAutoCommit(true);
      stmt.setInt(1, photoFile.getPhotoNo());
      stmt.setString(2, photoFile.getFilePath());

      return stmt.executeUpdate();
    }
  }


  @Override
  public List<PhotoFile> findAll(int photoNo) throws Exception {

    try(PreparedStatement stmt = con.prepareStatement(
        "SELECT photo_file_id, photo_id, file_path"
            + " FROM lms_photo_file"
            + " where photo_id = ?")) {

      con.setAutoCommit(true);
      stmt.setInt(1, photoNo);
      ResultSet rs = stmt.executeQuery();

      List<PhotoFile> list = new ArrayList<>();
      while(rs.next()) {

        list.add(new PhotoFile()
            .setNo(rs.getInt("photo_file_id"))
            .setPhotoNo(rs.getInt("photo_id"))
            .setFilePath(rs.getString("file_path")));
      }
      return list;
    }
  }



  @Override
  public int deleteAll(int photoNo) throws Exception {

    try(PreparedStatement stmt = con.prepareStatement(
        "DELETE from lms_photo_file WHERE photo_id = ?")) {

      con.setAutoCommit(true);
      stmt.setInt(1, photoNo);

      return stmt.executeUpdate();
    }
  }







}
