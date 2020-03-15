package yk.opic.service;

import java.util.List;
import yk.opic.project.domain.Lesson;

public interface LessonService {

  int add(Lesson lesson) throws Exception;

  int delete(int lessonNo) throws Exception;

  Lesson get(int lessonNo) throws Exception;

  int update(Lesson lesson) throws Exception;

  List<Lesson> list() throws Exception;
}
