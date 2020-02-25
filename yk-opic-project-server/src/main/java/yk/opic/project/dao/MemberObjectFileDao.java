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
import yk.opic.project.domain.Member;

public class MemberObjectFileDao {

  File file;
  List<Member> list;
  
  public MemberObjectFileDao(String fileName) {
    this.file = new File(fileName);
    list = new LinkedList<>();
  }

  @SuppressWarnings("unchecked")
  public void loadData() {
    File file = new File("./member.ser");
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      list = (LinkedList<Member>)in.readObject();

      System.out.printf("총 %d개 멤버 로딩하였습니다.\n", list.size());

    } catch(Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  public void saveData() {
    File file = new File("./member.ser");
    try(ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))){

      out.writeObject(list);

      System.out.printf("총 %d개 멤버정보를 저장하였습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }
}
