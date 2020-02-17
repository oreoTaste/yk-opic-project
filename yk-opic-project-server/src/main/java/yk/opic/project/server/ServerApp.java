package yk.opic.project.server;

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
import yk.opic.project.server.context.ApplicationContextListener;
import yk.opic.project.server.domain.Board;
import yk.opic.project.server.domain.Lesson;
import yk.opic.project.server.domain.Member;

// v32_3
public class ServerApp {
  static Scanner scanner = new Scanner(System.in);

  static List<ApplicationContextListener> listeners = new ArrayList<>();
  static HashMap<String, Object> context = new LinkedHashMap<>();

  private void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

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

  public static void main(String[] args) {
    System.out.println("서버 시작");

    try {
      ServerApp serverApp = new ServerApp();
      serverApp.addApplicationContextListener(new DataLoaderListener());
      notifyApplicationInitialized();
      serverApp.service();
      notifyApplicationDestroyed();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }


  public void service() throws IOException {

    notifyApplicationInitialized();

    try(ServerSocket serverSocket = new ServerSocket(9999)){
      System.out.println("서버 연결 완료");

      System.out.println("...클라이언트 접속 대기");
      processRequest(serverSocket);

    } catch (Exception e) {
      System.out.println("서버 준비중 오류발생");
    }
  }


  @SuppressWarnings("unchecked")
  private static void processRequest(ServerSocket serverSocket) throws Exception {
    while(true) {
      try(Socket socket = serverSocket.accept();
          ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

        String request = in.readUTF();

        if(request.equalsIgnoreCase("quit")) {
          out.writeUTF("OK");
          out.flush();
        }

        List<Board> boardList = (List<Board>) context.get("boardList");
        List<Member> memberList = (List<Member>) context.get("memberList");
        List<Lesson> lessonList = (List<Lesson>) context.get("lessonList");


        if(request.equalsIgnoreCase("/board/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(boardList);

        } else if(request.equalsIgnoreCase("/board/add")) {

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
          } else {
            out.writeUTF("FAIL");
            out.writeUTF("같은 번호의 게시물이 있습니다.");
          }


        } else if(request.equalsIgnoreCase("/board/detail")) {

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


        } else if(request.equalsIgnoreCase("/board/update")) {
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
          } else {
            boardList.set(index, board);
            out.writeUTF("OK");
          }

        } else if(request.equalsIgnoreCase("/board/delete")) {

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
            boardList.remove(index);
            out.writeUTF("OK");
          }

        } else if(request.equalsIgnoreCase("/lesson/list")) {

        } else if(request.equalsIgnoreCase("/lesson/add")) {

        } else if(request.equalsIgnoreCase("/lesson/detail")) {

        } else if(request.equalsIgnoreCase("/lesson/update")) {

        } else if(request.equalsIgnoreCase("/lesson/delete")) {

        } else if(request.equalsIgnoreCase("/member/list")) {

        } else if(request.equalsIgnoreCase("/member/add")) {

        } else if(request.equalsIgnoreCase("/member/detail")) {

        } else if(request.equalsIgnoreCase("/member/update")) {

        } else if(request.equalsIgnoreCase("/member/delete")) {

        }




      }
    }
  }
}
