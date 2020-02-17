package yk.opic.project.client;

import java.io.IOException;
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
import yk.opic.project.client.handler.BoardAddCommand;
import yk.opic.project.client.handler.BoardDeleteCommand;
import yk.opic.project.client.handler.BoardDetailCommand;
import yk.opic.project.client.handler.BoardListCommand;
import yk.opic.project.client.handler.BoardUpdateCommand;
import yk.opic.project.client.handler.Command;
import yk.opic.project.client.util.Prompt;

//v32_3
public class ClientApp {
  static Scanner scanner = new Scanner(System.in);
  static Prompt prompt = new Prompt(scanner);
  static Queue<String> commandQueue = new LinkedList<>();
  static Deque<String> commandStack = new ArrayDeque<>();

  public static void main(String[] args) {

    System.out.println("클라이언트 시작");

    try {
      ClientApp clientApp = new ClientApp();
      clientApp.service();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void service() throws Exception {
    String serverAddr = prompt.inputString("서버주소 : ");
    int portNumber = prompt.inputInt("포트번호 : ");

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

    while(true) {
      HashMap<String, Command> hashmap = new HashMap<>();
      hashmap.put("/board/add", new BoardAddCommand(prompt, out, in));
      hashmap.put("/board/delete", new BoardDeleteCommand(prompt, out, in));
      hashmap.put("/board/detail", new BoardDetailCommand(prompt, out, in));
      hashmap.put("/board/list", new BoardListCommand(out, in));
      hashmap.put("/board/update", new BoardUpdateCommand(prompt, out, in));
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
      System.out.println();
      String command = prompt.inputString("명령> ");
      commandStack.push(command);
      commandQueue.offer(command);

      if (command.length() == 0) {
        continue;
      }
      if (command.equalsIgnoreCase("quit")) {
        try {
          out.writeUTF("quit");
          break;
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (command.equals("history")) {
        printCommandHistory(commandQueue.iterator());
      } else if (command.equals("history2")) {
        printCommandHistory(commandStack.iterator());
      }

      Command commandHandler = hashmap.get(command);
      if (commandHandler != null) {
        commandHandler.execute();
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
