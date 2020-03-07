package yk.opic.project.dao;

import java.util.List;
import yk.opic.project.domain.PhotoBoard;

public interface PhotoBoardDao {

  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> findAll() throws Exception;

  List<PhotoBoard> findAllByLessonNo(int lessonNo) throws Exception;

  PhotoBoard findByNo(int photoNo) throws Exception;

  int update(PhotoBoard photoBoard) throws Exception;

  int delete(int no) throws Exception;

}
