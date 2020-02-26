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
import yk.opic.project.dao.BoardObjectFileDao;
import yk.opic.project.dao.LessonObjectFileDao;
import yk.opic.project.dao.MemberObjectFileDao;
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
import yk.opic.project.servlet.Servlet;

// v32_6
public class ServerApp {
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


  @SuppressWarnings("unchecked")
  public void service() throws IOException {

    notifyApplicationInitialized();
    
    BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser");
    LessonObjectFileDao lessonDao = new LessonObjectFileDao("./lesson.ser");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("./member.ser");
    
    servletMap = new HashMap<>();
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));

    servletMap.put("/lesson/add", new LessonAddServlet(lessonDao));
    servletMap.put("/lesson/delete", new LessonDeleteServlet(lessonDao));
    servletMap.put("/lesson/detail", new LessonDetailServlet(lessonDao));
    servletMap.put("/lesson/list", new LessonListServlet(lessonDao));
    servletMap.put("/lesson/update", new LessonUpdateServlet(lessonDao));

    /*
    servletMap.put("/member/add", new MemberAddServlet(memberList));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberList));
    servletMap.put("/member/detail", new MemberDetailServlet(memberList));
    servletMap.put("/member/list", new MemberListServlet(memberList));
    servletMap.put("/member/update", new MemberUpdateServlet(memberList));
    */
    
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


  private void processRequest(Socket clientSocket) throws Exception {

    try(Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      while(true) {
        String request = in.readUTF();

        if(request.equalsIgnoreCase("quit") || request.equalsIgnoreCase("/server/stop")) {
          break;
        }



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
