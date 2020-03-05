package yk.opic.project.dao;

import java.util.List;
import yk.opic.project.domain.PhotoFile;

public interface PhotoFileDao {
  int insert(PhotoFile photoFile) throws Exception;

  List<PhotoFile> findAll(int photoNo) throws Exception;

  int deleteAll(int photoNo) throws Exception;

}
