package yk.opic.project.service;

import java.util.List;
import yk.opic.project.domain.PhotoBoard;

public interface PhotoBoardService {

  int add(PhotoBoard photoBoard) throws Exception;

  int delete(int photoBoardNo) throws Exception;

  PhotoBoard get(int photoNo) throws Exception;
  List<PhotoBoard> getLesson(int lessonNo) throws Exception;

  int update(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> list() throws Exception;
}
