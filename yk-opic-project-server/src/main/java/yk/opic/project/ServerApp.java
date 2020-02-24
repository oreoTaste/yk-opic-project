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
import java.util.Map;
import java.util.Scanner;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.domain.Board;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.Member;
import yk.opic.project.servlet.BoardAddServlet;
import yk.opic.project.servlet.BoardDeleteServlet;
import yk.opic.project.servlet.BoardDetailServlet;
import yk.opic.project.servlet.BoardListServlet;
import yk.opic.project.servlet.BoardUpdateServlet;
import yk.opic.project.servlet.LessonAddServlet;
import yk.opic.project.servlet.LessonDeleteServlet;
import yk.opic.project.servlet.LessonDetailServlet;
import yk.opic.project.servlet.LessonListServlet;
import yk.opic.project.servlet.LessonUpdateServlet;
import yk.opic.project.servlet.MemberAddServlet;
import yk.opic.project.servlet.MemberDeleteServlet;
import yk.opic.project.servlet.MemberDetailServlet;
import yk.opic.project.servlet.MemberListServlet;
import yk.opic.project.servlet.MemberUpdateServlet;
import yk.opic.project.servlet.Servlet;

// v32_6
public class ServerApp {
  List<Board> boardList;
  List<Member> memberList;
  List<Lesson> lessonList;
  Map<String, Servlet> servletMap;

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

    servletMap = new HashMap<>();
    servletMap.put("/board/add", new BoardAddServlet(boardList));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardList));
    servletMap.put("/board/detail", new BoardDetailServlet(boardList));
    servletMap.put("/board/list", new BoardListServlet(boardList));
    servletMap.put("/board/update", new BoardUpdateServlet(boardList));

    servletMap.put("/lesson/add", new LessonAddServlet(lessonList));
    servletMap.put("/lesson/delete", new LessonDeleteServlet(lessonList));
    servletMap.put("/lesson/detail", new LessonDetailServlet(lessonList));
    servletMap.put("/lesson/list", new LessonListServlet(lessonList));
    servletMap.put("/lesson/update", new LessonUpdateServlet(lessonList));

    servletMap.put("/member/add", new MemberAddServlet(memberList));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberList));
    servletMap.put("/member/detail", new MemberDetailServlet(memberList));
    servletMap.put("/member/list", new MemberListServlet(memberList));
    servletMap.put("/member/update", new MemberUpdateServlet(memberList));

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

        Servlet servlet = servletMap.get(request);

        if(servlet == null) {
          out.writeUTF("FAIL");
          out.flush();
          out.writeUTF("요청한 명령을 처리할 수 없습니다.");
          out.flush();
        } else {
          try {
            servlet.service(in, out);
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.flush();
            out.writeUTF(e.getMessage());
            out.flush();

            System.out.println("클라이언트 요청 처리 중 오류 발생:");
            e.printStackTrace();
          }
        }

      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }




}
