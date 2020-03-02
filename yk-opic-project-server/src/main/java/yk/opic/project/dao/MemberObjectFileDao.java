package yk.opic.project.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import yk.opic.project.domain.Member;

public class MemberObjectFileDao extends AbstractObjectFileDao<Member> {

  public MemberObjectFileDao(String fileName) {
    super(fileName);
    loadData();
  }

  @Override
  @SuppressWarnings("unchecked")
  public void loadData() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      list = (LinkedList<Member>)in.readObject();

      System.out.printf("총 %d개 멤버 로딩하였습니다.\n", list.size());

    } catch(Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  @Override
  public void saveData() {
    try(ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))){

      out.writeObject(list);

      System.out.printf("총 %d개 멤버정보를 저장하였습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }

  public int insert(Member member) throws Exception {
    int index = indexOf(member.getNo());
    if(index == 0)
      return 0;

    list.add(member);
    saveData();
    return 1;
  }

  public Member findByNo(int no) throws Exception {
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return null;
    }
    saveData();
    return list.get(index);
  }

  public List<Member> findAll() throws Exception {
    return list;
  }

  public int delete(int no) throws Exception {

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return 0;
    }

    list.remove(index);
    saveData();
    System.out.println("회원을 삭제했습니다.");
    return 1;
  }

  public int update(Member member) throws Exception {

    int index = indexOf(member.getNo());

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return 0;
    }

    Member oldMember = list.get(index);

    if (member.equals(oldMember)) {
      System.out.println("회원 변경을 취소했습니다.");
      return 0;
    } else {
      list.set(index, member);
      saveData();
      System.out.println("회원을 변경했습니다.");
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