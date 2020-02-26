package yk.opic.project.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractObjectFileDao<T>{

  File file;
  List<T> list;

  public AbstractObjectFileDao(String fileName) {
    this.file = new File(fileName);
    list = new LinkedList<>();
    loadData();
  }

  @SuppressWarnings("unchecked")
  public void loadData() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      list = (LinkedList<T>)in.readObject();

      System.out.printf("총 %d개 게시글 로딩하였습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  public void saveData() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeObject(list);

      System.out.printf("총 %d개 게시글 저장하였습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }

  abstract int indexOf(int no) throws Exception;

}
