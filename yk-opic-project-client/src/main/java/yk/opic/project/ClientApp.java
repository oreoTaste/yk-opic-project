package yk.opic.project;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import yk.opic.project.handler.BoardAddCommand;
import yk.opic.project.handler.BoardDeleteCommand;
import yk.opic.project.handler.BoardDetailCommand;
import yk.opic.project.handler.BoardListCommand;
import yk.opic.project.handler.BoardUpdateCommand;
import yk.opic.project.handler.Command;
import yk.opic.project.util.Prompt;

//v32_3
public class ClientApp {
  static Scanner scanner = new Scanner(System.in);
  static Prompt prompt = new Prompt(scanner);

  public static void main(String[] args) throws Exception {

    System.out.println("클라이언트 시작");

    ClientApp clientApp = new ClientApp();
    clientApp.service();

  }

  public void service() {
    String serverAddr;
    serverAddr = "localhost";//prompt.inputString("서버주소 : ");
    int portNumber;
    portNumber = 9999;//prompt.inputInt("포트번호 : ");

    try(Socket socket = new Socket(serverAddr, portNumber);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버 접속완료");
      processCommand(out, in);

    } catch(Exception e) {
      System.out.println("오류발생");
    }

    scanner.close();
  }


  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    Queue<String> commandQueue = new LinkedList<>();
    Deque<String> commandStack = new ArrayDeque<>();
    HashMap<String, Command> hashmap = new HashMap<>();

    hashmap.put("/board/add", new BoardAddCommand(out, in, prompt));
    hashmap.put("/board/delete", new BoardDeleteCommand(out, in, prompt));
    hashmap.put("/board/detail", new BoardDetailCommand(out, in, prompt));
    hashmap.put("/board/list", new BoardListCommand(out, in));
    hashmap.put("/board/update", new BoardUpdateCommand(out, in, prompt));

    /*
    hashmap.put("/lesson/add", new LessonAddCommand(prompt, lessonList));
    hashmap.put("/lesson/delete", new LessonDeleteCommand(prompt, lessonList));
    hashmap.put("/lesson/detail", new LessonDetailCommand(prompt, lessonList));
    hashmap.put("/lesson/list", new LessonListCommand(lessonList));
    hashmap.put("/lesson/update", new LessonUpdateCommand(prompt, lessonList));

    hashmap.put("/member/add", new MemberAddCommand(prompt, memberList));
    hashmap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    hashmap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    hashmap.put("/member/list", new MemberListCommand(memberList));
    hashmap.put("/member/update", new MemberUpdateCommand(prompt, memberList));
     */

    while(true) {
      String command = prompt.inputString("\n명령> ");
      commandStack.push(command);
      commandQueue.offer(command);

      if (command.length() == 0) {
        continue;
      }
      if (command.equalsIgnoreCase("quit")) {
        System.out.println("...안녕");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandStack.iterator());
        continue;
      }


      Command commandHandler = hashmap.get(command);
      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.println("명령어 실행중 오류발생 : " + e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
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
