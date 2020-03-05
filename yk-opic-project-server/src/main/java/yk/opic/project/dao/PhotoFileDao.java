package yk.opic.project.dao;

import java.util.List;
import yk.opic.project.domain.PhotoFile;

public interface PhotoFileDao {

  int insert(PhotoFile photoFile) throws Exception;

  List<PhotoFile> findAll(int photoNo) throws Exception;

  PhotoFile findByNo(int no) throws Exception;

  int update(PhotoFile photoFile) throws Exception;

  int delete(int no) throws Exception;

}
