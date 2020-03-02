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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.dao.MemberDao;
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

public class ServerApp {
  Map<String, Servlet> servletMap = new HashMap<>();
  List<ApplicationContextListener> listeners = new ArrayList<>();
  HashMap<String, Object> context = new LinkedHashMap<>();

  // 스레드풀 생산!
  ExecutorService executorService = Executors.newCachedThreadPool();

  private void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  @SuppressWarnings("unused")
  private void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for(ApplicationContextListener list : listeners) {
      list.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
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

    BoardDao boardDao = (BoardDao) context.get("boardDao");
    LessonDao lessonDao = (LessonDao) context.get("lessonDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");

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

    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));

    try(ServerSocket serverSocket = new ServerSocket(9999)){
      System.out.println("서버 연결 완료");

      while(true) {
        Socket socket = serverSocket.accept();
        System.out.println("...클라이언트 접속!");

        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("=========================");
        });

        new Thread().start();

      }

    } catch (Exception e) {
      System.out.println("서버 준비중 오류발생");
    }

    notifyApplicationDestroyed();

    executorService.shutdown();
  }


  private int processRequest(Socket clientSocket) {

    try(Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      String request = in.readUTF();

      if(request.equalsIgnoreCase("/server/stop")) {
        return 9;
      }

      Servlet servlet = servletMap.get(request);

      if(servlet == null) {
        notFound(out);
      } else {
        try {
          servlet.service(in, out);
        } catch (Exception e) {
          out.writeUTF("FAIL");
          out.writeUTF(e.getMessage());
          out.flush();

          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      }

      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  private void notFound(ObjectOutputStream out) throws Exception {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
    out.flush();
  }




}
