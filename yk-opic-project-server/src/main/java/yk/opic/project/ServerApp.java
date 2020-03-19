package yk.opic.project;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.session.SqlSessionFactory;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.service.BoardService;
import yk.opic.project.service.LessonService;
import yk.opic.project.service.MemberService;
import yk.opic.project.service.PhotoBoardService;
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
import yk.opic.project.servlet.LoginServlet;
import yk.opic.project.servlet.MemberAddServlet;
import yk.opic.project.servlet.MemberDeleteServlet;
import yk.opic.project.servlet.MemberDetailServlet;
import yk.opic.project.servlet.MemberListServlet;
import yk.opic.project.servlet.MemberSearchServlet;
import yk.opic.project.servlet.MemberUpdateServlet;
import yk.opic.project.servlet.PhotoBoardAddServlet;
import yk.opic.project.servlet.PhotoBoardDeleteServlet;
import yk.opic.project.servlet.PhotoBoardDetailServlet;
import yk.opic.project.servlet.PhotoBoardListServlet;
import yk.opic.project.servlet.PhotoBoardUpdateServlet;
import yk.opic.project.servlet.Servlet;
import yk.opic.sql.SqlSessionFactoryProxy;

public class ServerApp {
  Map<String, Servlet> servletMap = new HashMap<>();
  List<ApplicationContextListener> listeners = new ArrayList<>();
  HashMap<String, Object> context = new LinkedHashMap<>();
  boolean serverStop = false;

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

    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.get("sqlSessionFactory");
    BoardService boardService = (BoardService) context.get("boardService");
    LessonService lessonService = (LessonService) context.get("lessonService");
    MemberService memberService = (MemberService) context.get("memberService");
    PhotoBoardService photoBoardService = (PhotoBoardService) context.get("photoBoardService");

    servletMap.put("/board/add", new BoardAddServlet(boardService));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardService));
    servletMap.put("/board/detail", new BoardDetailServlet(boardService));
    servletMap.put("/board/list", new BoardListServlet(boardService));
    servletMap.put("/board/update", new BoardUpdateServlet(boardService));

    servletMap.put("/lesson/add", new LessonAddServlet(lessonService));
    servletMap.put("/lesson/delete", new LessonDeleteServlet(lessonService));
    servletMap.put("/lesson/detail", new LessonDetailServlet(lessonService));
    servletMap.put("/lesson/list", new LessonListServlet(lessonService));
    servletMap.put("/lesson/update", new LessonUpdateServlet(lessonService));

    servletMap.put("/member/add", new MemberAddServlet(memberService));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberService));
    servletMap.put("/member/detail", new MemberDetailServlet(memberService));
    servletMap.put("/member/list", new MemberListServlet(memberService));
    servletMap.put("/member/update", new MemberUpdateServlet(memberService));
    servletMap.put("/member/search", new MemberSearchServlet(memberService));
    servletMap.put("/auth/login", new LoginServlet(memberService));

    servletMap.put("/photoboard/list", new PhotoBoardListServlet(
        photoBoardService, lessonService));
    servletMap.put("/photoboard/detail", new PhotoBoardDetailServlet(
        photoBoardService));
    servletMap.put("/photoboard/add", new PhotoBoardAddServlet(
        photoBoardService, lessonService));
    servletMap.put("/photoboard/update", new PhotoBoardUpdateServlet(
        photoBoardService));
    servletMap.put("/photoboard/delete", new PhotoBoardDeleteServlet(
        photoBoardService));


    try(ServerSocket serverSocket = new ServerSocket(9999)){
      System.out.println("서버 연결 완료");

      while(true) {
        Socket socket = serverSocket.accept();
        System.out.println("...클라이언트 접속!");

        executorService.submit(() -> {
          processRequest(socket);
          ((SqlSessionFactoryProxy)sqlSessionFactory).closeSession();
          System.out.println("=========================");
          return;
        });

        if(serverStop)
          break;

      }

    } catch (Exception e) {
      System.out.println("서버 준비중 오류발생");
    }

    notifyApplicationDestroyed();

    executorService.shutdown();
  }


  private int processRequest(Socket clientSocket) {

    try(Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      if(request.equalsIgnoreCase("/server/stop")) {
        out.println("서버를 종료하려면 /server/stop을 한번더 입력해주세요");
        serverStop();
        return 9;
      }

      if(request.equalsIgnoreCase("/quit")) {
        out.println("클라이언트 종료하려면 quit을 한번더 입력해주세요");
        out.println("!end!");
        return 0;
      }

      Servlet servlet = servletMap.get(request);

      if(servlet == null) {
        notFound(out);
      } else {
        try {
          servlet.service(in, out);
        } catch (Exception e) {
          out.println(e.getMessage());
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

  private void serverStop() {
    serverStop = true;
    return;
  }

  private void notFound(PrintStream out) throws Exception {
    out.println("요청한 명령을 처리할 수 없습니다.");
    out.flush();
  }




}
