package yk.opic.project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.domain.Board;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.Member;

// v32_5
public class ServerApp {
  List<Board> boardList;
  List<Member> memberList;
  List<Lesson> lessonList;
  static Scanner scanner = new Scanner(System.in);

  static List<ApplicationContextListener> listeners = new ArrayList<>();
  static HashMap<String, Object> context = new LinkedHashMap<>();

  private void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  @SuppressWarnings("unused")
  private void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private static void notifyApplicationInitialized() {
    for(ApplicationContextListener list : listeners) {
      list.contextInitialized(context);
    }
  }

  private static void notifyApplicationDestroyed() {
    for(ApplicationContextListener list : listeners) {
      list.contextDestroyed(context);
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println("서버 시작");

    ServerApp serverApp = new ServerApp();
    serverApp.addApplicationContextListener(new DataLoaderListener());
    serverApp.service();
  }


  public void service() throws IOException {

    notifyApplicationInitialized();

    try(ServerSocket serverSocket = new ServerSocket(9999)){
      System.out.println("서버 연결 완료");

      while(true) {
        Socket socket = serverSocket.accept();
        System.out.println("...클라이언트 접속 대기");
        processRequest(socket);
        System.out.println("=========================");
      }

    } catch (Exception e) {
      System.out.println("서버 준비중 오류발생");
    }

    notifyApplicationDestroyed();
  }


  @SuppressWarnings("unchecked")
  private void processRequest(Socket clientSocket) throws Exception {

    try(Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      while(true) {
        String request = in.readUTF();

        if(request.equalsIgnoreCase("quit") || request.equalsIgnoreCase("/server/stop")) {
          break;
        }
        boardList = (List<Board>) context.get("boardList");
        memberList = (List<Member>) context.get("memberList");
        lessonList = (List<Lesson>) context.get("lessonList");
        switch (request) {
          case "/board/list":  listBoard(in, out); break;
          case "/board/add": addBoard(in, out); break;
          case "/board/detail": detailBoard(in, out); break;
          case "/board/update": updateBoard(in, out); break;
          case "/board/delete": deleteBoard(in, out); break;
          case "/lesson/list": listLesson(in, out); break;
          case "/lesson/add": addLesson(in, out); break;
          case "/lesson/detail": detailLesson(in, out); break;
          case "/lesson/update": updateLesson(in, out); break;
          case "/lesson/delete": deleteLesson(in, out); break;
          case "/member/list": listMember(in, out); break;
          case "/member/add": addMember(in, out); break;
          case "/member/detail": detailMember(in, out); break;
          case "/member/update": updateMember(in, out); break;
          case "/member/delete": deleteMember(in, out); break;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void deleteLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = 0;
    for(; index < lessonList.size(); index++) {

      if(lessonList.get(index).getNo() == no) {
        break;
      }

    }

    if(index == lessonList.size()) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      lessonList.remove(index);
      out.writeUTF("OK");
      out.flush();
    }
  }

  private void listMember(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(memberList);
  }

  private void addMember(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    try {
      Member member = (Member) in.readObject();

      int index = 0;
      for(; index < memberList.size(); index++) {

        if(memberList.get(index).getNo() == member.getNo()) {
          break;
        }

      }

      if(index == memberList.size()) {
        memberList.add(member);
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.flush();
        out.writeUTF("같은 번호의 멤버 정보가 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

  private void detailMember(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    int index = 0;
    for(; index < memberList.size(); index++) {

      if(memberList.get(index).getNo() == no) {
        break;
      }

    }

    if(index == memberList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 멤버정보가 없습니다.");
    } else {
      out.writeUTF("OK");
      out.writeObject(memberList.get(index));
    }
  }

  private void updateMember(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Member member = new Member();
    member = (Member) in.readObject();

    int index = 0;
    for(; index < memberList.size(); index++) {

      if(memberList.get(index).getNo() == member.getNo()) {
        break;
      }

    }

    if(index == memberList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 멤버정보가 없습니다.");
      out.flush();
    } else {
      memberList.set(index, member);
      out.writeUTF("OK");
      out.flush();
    }
  }

  private void deleteMember(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = 0;
    for(; index < lessonList.size(); index++) {

      if(memberList.get(index).getNo() == no) {
        break;
      }

    }

    if(index == memberList.size()) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF("해당 번호의 멤버정보가 없습니다.");
      out.flush();
    } else {
      memberList.remove(index);
      out.writeUTF("OK");
      out.flush();
    }
  }

  private void updateLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = new Lesson();
    lesson = (Lesson) in.readObject();

    int index = 0;
    for(; index < lessonList.size(); index++) {

      if(lessonList.get(index).getNo() == lesson.getNo()) {
        break;
      }

    }

    if(index == lessonList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업정보가 없습니다.");
      out.flush();
    } else {
      lessonList.set(index, lesson);
      out.writeUTF("OK");
      out.flush();
    }
  }

  private void detailLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = 0;
    for(; index < lessonList.size(); index++) {

      if(lessonList.get(index).getNo() == no) {
        break;
      }

    }

    if(index == lessonList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업정보가 없습니다.");
    } else {
      out.writeUTF("OK");
      out.writeObject(lessonList.get(index));
    }
  }

  private void addLesson(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Lesson lesson = (Lesson) in.readObject();

      int index = 0;
      for(; index < lessonList.size(); index++) {

        if(lessonList.get(index).getNo() == lesson.getNo()) {
          break;
        }

      }

      if(index == lessonList.size()) {
        lessonList.add(lesson);
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.flush();
        out.writeUTF("같은 번호의 수업정보가 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

  private void listLesson(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(lessonList);
  }

  private void deleteBoard(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = 0;
    for(; index < boardList.size(); index++) {

      if(boardList.get(index).getNo() == no) {
        break;
      }
    }

    if(index == boardList.size()) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      boardList.remove(index);
      out.writeUTF("OK");
      out.flush();
    }
  }

  private void updateBoard(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = new Board();
    board = (Board) in.readObject();

    int index = 0;
    for(; index < boardList.size(); index++) {

      if(boardList.get(index).getNo() == board.getNo()) {
        break;
      }

    }

    if(index == boardList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
      out.flush();
    } else {
      boardList.set(index, board);
      out.writeUTF("OK");
      out.flush();
    }
  }

  private void detailBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    int no = in.readInt();

    int index = 0;
    for(; index < boardList.size(); index++) {

      if(boardList.get(index).getNo() == no) {
        break;
      }

    }

    if(index == boardList.size()) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    } else {
      out.writeUTF("OK");
      out.writeObject(boardList.get(index));
    }
  }

  private void addBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Board board = (Board) in.readObject();

      int index = 0;
      for(; index < boardList.size(); index++) {

        if(boardList.get(index).getNo() == board.getNo()) {
          break;
        }

      }

      if(index == boardList.size()) {
        boardList.add(board);
        out.writeUTF("OK");
        out.flush();
      } else {
        out.writeUTF("FAIL");
        out.flush();
        out.writeUTF("같은 번호의 게시물이 있습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.writeUTF("FAIL");
      out.flush();
      out.writeUTF(e.getMessage());
      out.flush();
      e.printStackTrace();
    }
  }

  private void listBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(boardList);
  }
}
