package yk.opic.project.dao;

import java.util.List;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;

public interface PhotoFileDao {
  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoFile> findAll(int photoNo) throws Exception;

  int deleteAll(int photoNo) throws Exception;
}
