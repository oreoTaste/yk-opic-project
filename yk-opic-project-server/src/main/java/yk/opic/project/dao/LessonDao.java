package yk.opic.project.dao;

import java.util.List;
import yk.opic.project.domain.Lesson;

public interface LessonDao {

  Lesson findByNo(int no) throws Exception;

  List<Lesson> findAll() throws Exception;

  int delete(int no) throws Exception;

  int insert(Lesson lesson) throws Exception;

  int update(Lesson lesson) throws Exception;
}
