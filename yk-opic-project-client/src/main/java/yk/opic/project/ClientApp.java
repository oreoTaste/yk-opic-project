package yk.opic.project;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.dao.proxy.BoardDaoProxy;
import yk.opic.project.dao.proxy.DaoProxyHelper;
import yk.opic.project.dao.proxy.LessonDaoProxy;
import yk.opic.project.dao.proxy.MemberDaoProxy;
import yk.opic.project.handler.BoardAddCommand;
import yk.opic.project.handler.BoardDeleteCommand;
import yk.opic.project.handler.BoardDetailCommand;
import yk.opic.project.handler.BoardListCommand;
import yk.opic.project.handler.BoardUpdateCommand;
import yk.opic.project.handler.Command;
import yk.opic.project.handler.LessonAddCommand;
import yk.opic.project.handler.LessonDeleteCommand;
import yk.opic.project.handler.LessonDetailCommand;
import yk.opic.project.handler.LessonListCommand;
import yk.opic.project.handler.LessonUpdateCommand;
import yk.opic.project.handler.MemberAddCommand;
import yk.opic.project.handler.MemberDeleteCommand;
import yk.opic.project.handler.MemberDetailCommand;
import yk.opic.project.handler.MemberListCommand;
import yk.opic.project.handler.MemberUpdateCommand;
import yk.opic.project.util.Prompt;

public class ClientApp {
  static Scanner scanner = new Scanner(System.in);
  static Prompt prompt = new Prompt(scanner);

  Queue<String> commandQueue;
  Deque<String> commandStack;

  String serverAddr = null;
  int portNumber = 0;

  public ClientApp() {
    commandQueue = new LinkedList<>();
    commandStack = new ArrayDeque<>();
  }

  public static void main(String[] args) throws Exception {

    System.out.println("클라이언트 시작");

    ClientApp clientApp = new ClientApp();
    clientApp.service();

  }

  public void service() {
    try {
      serverAddr = prompt.inputString("서버주소 : ");
      portNumber = prompt.inputInt("포트번호 : ");
    } catch(Exception e) {
      System.out.println("서버주소 및 포트번호가 유효하지 않습니다.");
      scanner.close();
      return;
    }

    while(true) {
      String command = prompt.inputString("\n명령> ");

      if (command.length() == 0) {
        continue;
      }
      if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("/server/stop")) {
        System.out.println("...안녕");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandStack.iterator());
        continue;
      }

      commandStack.push(command);
      commandQueue.offer(command);
      System.out.println("<<");
      processCommand(command);
      System.out.println(">>");

    }
    scanner.close();
  }

  private void processCommand(String command) {

    try{
      DaoProxyHelper daoProxyHelper = new DaoProxyHelper(serverAddr, portNumber);
      BoardDao boardDao = new BoardDaoProxy(daoProxyHelper);
      MemberDao memberDao = new MemberDaoProxy(daoProxyHelper);
      LessonDao lessonDao = new LessonDaoProxy(daoProxyHelper);

      HashMap<String, Command> hashmap = new HashMap<>();
      hashmap.put("/board/add", new BoardAddCommand(boardDao, prompt));
      hashmap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));
      hashmap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
      hashmap.put("/board/list", new BoardListCommand(boardDao));
      hashmap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));

      hashmap.put("/lesson/add", new LessonAddCommand(lessonDao, prompt));
      hashmap.put("/lesson/delete", new LessonDeleteCommand(lessonDao, prompt));
      hashmap.put("/lesson/detail", new LessonDetailCommand(lessonDao, prompt));
      hashmap.put("/lesson/list", new LessonListCommand(lessonDao));
      hashmap.put("/lesson/update", new LessonUpdateCommand(lessonDao, prompt));

      hashmap.put("/member/add", new MemberAddCommand(memberDao, prompt));
      hashmap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));
      hashmap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
      hashmap.put("/member/list", new MemberListCommand(memberDao));
      hashmap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));

      Command commandHandler = hashmap.get(command);
      if (commandHandler != null) {
        commandHandler.execute();
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    } catch(Exception e) {
      System.out.println("명령어 실행중 오류발생 : " + e.getMessage());
      e.printStackTrace();
    }
  }
  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if (count % 5 == 0) {
        System.out.print(":(중지하고 싶으면 q)");
        String str = scanner.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

}
