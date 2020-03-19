package yk.opic.project.service.impl;

import java.util.List;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.service.LessonService;

public class LessonServiceImpl implements LessonService {
  LessonDao lessonDao;

  public LessonServiceImpl(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public int add(Lesson lesson) throws Exception {
    return lessonDao.insert(lesson);
  }

  @Override
  public int delete(int lessonNo) throws Exception {
    return delete(lessonNo);
  }

  @Override
  public Lesson get(int lessonNo) throws Exception {
    return lessonDao.findByNo(lessonNo);
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    return lessonDao.update(lesson);
  }

  @Override
  public List<Lesson> list() throws Exception {
    return lessonDao.findAll();
  }

}
