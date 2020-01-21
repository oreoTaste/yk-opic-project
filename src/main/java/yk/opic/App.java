package yk.opic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import yk.opic.domain.Board;
import yk.opic.domain.Lesson;
import yk.opic.domain.Member;
import yk.opic.handler.BoardAddCommand;
import yk.opic.handler.BoardDeleteCommand;
import yk.opic.handler.BoardDetailCommand;
import yk.opic.handler.BoardListCommand;
import yk.opic.handler.BoardUpdateCommand;
import yk.opic.handler.Command;
import yk.opic.handler.ComputePlusCommand;
import yk.opic.handler.HelloCommand;
import yk.opic.handler.LessonAddCommand;
import yk.opic.handler.LessonDeleteCommand;
import yk.opic.handler.LessonDetailCommand;
import yk.opic.handler.LessonListCommand;
import yk.opic.handler.LessonUpdateCommand;
import yk.opic.handler.MemberAddCommand;
import yk.opic.handler.MemberDeleteCommand;
import yk.opic.handler.MemberDetailCommand;
import yk.opic.handler.MemberListCommand;
import yk.opic.handler.MemberUpdateCommand;
import yk.opic.util.Prompt;

public class App {
  static Scanner scanner = new Scanner(System.in);
  static Prompt prompt = new Prompt(scanner);

  static Queue<String> commandQueue = new LinkedList<>();
  static Deque<String> commandStack = new ArrayDeque<>();

  public static void main(String[] args) {
    HashMap<String, Command> hashmap = new HashMap<>();
    LinkedList<Board> boardList = new LinkedList<>();
    hashmap.put("/board/add", new BoardAddCommand(prompt, boardList));
    hashmap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));
    hashmap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    hashmap.put("/board/list", new BoardListCommand(boardList));
    hashmap.put("/board/update", new BoardUpdateCommand(prompt, boardList));

    ArrayList<Lesson> lessonList = new ArrayList<>();
    hashmap.put("/lesson/add", new LessonAddCommand(prompt, lessonList));
    hashmap.put("/lesson/delete", new LessonDeleteCommand(prompt, lessonList));
    hashmap.put("/lesson/detail", new LessonDetailCommand(prompt, lessonList));
    hashmap.put("/lesson/list", new LessonListCommand(lessonList));
    hashmap.put("/lesson/update", new LessonUpdateCommand(prompt, lessonList));

    LinkedList<Member> memberList = new LinkedList<>();
    hashmap.put("/member/add", new MemberAddCommand(prompt, memberList));
    hashmap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    hashmap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    hashmap.put("/member/list", new MemberListCommand(memberList));
    hashmap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    hashmap.put("/hello", new HelloCommand(prompt));
    hashmap.put("/compute/plus", new ComputePlusCommand(prompt));

    String command;
    firstInstruction();

    while (true) {
      System.out.println();
      System.out.print("명령> ");

      command = scanner.nextLine();
      commandStack.push(command);
      commandQueue.offer(command);

      if (command.length() == 0) {
        continue;
      }
      if (command.equalsIgnoreCase("quit")) {
        System.out.println("...안녕!");
        break;
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
    scanner.close();
  }

  private static void firstInstruction() {
    System.out.println("===============================================");
    System.out
        .print("명령어 모음 : /board/add, /../list, /../delete\n" + "\t\t     /../update, /../detail\n"
            + "\t/lesson/add, /../list, /../delete\n" + "\t\t     /../update, /../detail\n"
            + "\t/member/add, /../list, /../delete\n" + "\t\t     /../update, /../detail\n");
    System.out.println("===============================================");
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
/*
 * 번호? 1 이름? 홍길동 이메일? hong@test.com 암호? 1111 사진? hong.png 전화? 1111-2222
 *
 * 계속 입력하시겠습니까?(Y/n) y
 *
 * 번호? 2 이름? 임꺽정 이메일? lim@test.com 암호? 1111 사진? lim.png 전화? 1111-2223
 *
 * 계속 입력하시겠습니까?(Y/n) y
 *
 * 번호? 3 이름? 전봉준 이메일? jeon@test.com 암호? 1111 사진? jeon.png 전화? 1111-2224
 *
 * 계속 입력하시겠습니까?(Y/n) n
 *
 * 1, 홍길동 , hong@test.com , 1111-2222 , 2019-01-01 2, 임꺽정 , lim@test.com , 1111-2223 , 2019-01-01 3,
 * 전봉준 , jeon@test.com , 1111-2224 , 2019-01-01
 */
