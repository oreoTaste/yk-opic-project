package yk.opic.project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.domain.Board;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.Member;

public class DataLoaderListener implements ApplicationContextListener {
  ArrayList<Lesson> lessonList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Member> memberList = new LinkedList<>();

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    System.out.println("로딩시작");
    loadBoardData();
    loadLessonData();
    loadMemberData();

    context.put("lessonList", lessonList);
    context.put("boardList", boardList);
    context.put("memberList", memberList);
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");
    saveBoardData();
    saveLessonData();
    saveMemberData();
  }


  @SuppressWarnings("unchecked")
  private void loadLessonData() {
    File file = new File("./lesson.ser");

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))){
      while (true) {
        try {

          lessonList = (ArrayList<Lesson>) in.readObject();

          System.out.printf("총 %d개 수업정보를 로딩하였습니다.\n", lessonList.size());
        } catch (Exception e) {
          break;
        }
      }

    } catch (IOException e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    }
  }

  private void saveLessonData() {
    File file = new File("./lesson.ser");
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))){

      out.writeObject(lessonList);
      System.out.printf("총 %d개 수업정보를 저장하였습니다.\n", lessonList.size());

    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser");
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      memberList = (LinkedList<Member>)in.readObject();

      System.out.printf("총 %d개 멤버 로딩하였습니다.\n", memberList.size());

    } catch(Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private void saveMemberData() {
    File file = new File("./member.ser");
    try(ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))){

      out.writeObject(memberList);

      System.out.printf("총 %d개 멤버정보를 저장하였습니다.\n", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    File file = new File("./board.ser");

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      boardList = (LinkedList<Board>)in.readObject();

      System.out.printf("총 %d개 게시글 로딩하였습니다.\n", boardList.size());

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private void saveBoardData() {
    File file = new File("./board.ser");

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeObject(boardList);

      System.out.printf("총 %d개 게시글 저장하였습니다.\n", boardList.size());
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }

}
