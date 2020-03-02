package yk.opic.project.dao.json;

import java.util.List;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.domain.Lesson;

public class LessonJsonFileDao extends AbstractJsonFileDao<Lesson> implements LessonDao {

  public LessonJsonFileDao(String fileName) {
    super(fileName);
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return null;
    }
    return list.get(index);
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    return list;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return 0;
    }

    list.remove(index);
    saveData();
    System.out.println("수업을 삭제했습니다.");
    return 1;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    int index = indexOf(lesson.getNo());
    if(index > -1)
      return 0;

    list.add(lesson);
    saveData();
    return 1;
  }


  @Override
  public int update(Lesson lesson) throws Exception {
    int index = indexOf(lesson.getNo());

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return 0;
    }

    Lesson oldLesson = list.get(index);

    if (lesson.equals(oldLesson)) {
      System.out.println("수업 변경을 취소했습니다.");
      return 0;
    } else {
      list.set(index, lesson);
      saveData();
      System.out.println("수업을 변경했습니다.");
      return 1;
    }
  }


  @Override
  public int indexOf(int no) throws Exception {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no)
        return i;
    }
    return -1;
  }

}