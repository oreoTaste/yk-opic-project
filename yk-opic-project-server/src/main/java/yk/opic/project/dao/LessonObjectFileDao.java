package yk.opic.project.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.domain.Lesson;

public class LessonObjectFileDao extends AbstractObjectFileDao<Lesson> {

  public LessonObjectFileDao(String fileName) {
    super(fileName);
    loadData();
  }

  @Override
  @SuppressWarnings("unchecked")
  public void loadData() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))){

      list = (ArrayList<Lesson>) in.readObject();

      System.out.printf("총 %d개 수업정보를 로딩하였습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    }
  }

  @Override
  public void saveData() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))){

      out.reset();
      out.writeObject(list);
      System.out.printf("총 %d개 수업정보를 저장하였습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }


  public Lesson findByNo(int no) throws Exception {
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return null;
    }
    return list.get(index);
  }

  public List<Lesson> findAll() throws Exception {
    return list;
  }

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

  public int insert(Lesson lesson) throws Exception {
    int index = indexOf(lesson.getNo());
    if(index > -1)
      return 0;

    list.add(lesson);
    saveData();
    return 1;
  }


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