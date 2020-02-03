package yk.opic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.google.gson.Gson;
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
  static ArrayList<Lesson> lessonList = new ArrayList<>();
  static LinkedList<Board> boardList = new LinkedList<>();
  static LinkedList<Member> memberList = new LinkedList<>();

  public static void main(String[] args) {
    loadLessonData();
    loadMemberData();
    loadBoardData();

    HashMap<String, Command> hashmap = new HashMap<>();
    hashmap.put("/board/add", new BoardAddCommand(prompt, boardList));
    hashmap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));
    hashmap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    hashmap.put("/board/list", new BoardListCommand(boardList));
    hashmap.put("/board/update", new BoardUpdateCommand(prompt, boardList));

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
        saveLessonData();
        saveMemberData();
        saveBoardData();
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


  private static void loadLessonData() {
    File file = new File("./lesson.json");

    try (BufferedReader in = new BufferedReader(new FileReader(file))){
      while (true) {
        try {
          lessonList.addAll(Arrays.asList(new Gson().fromJson(in, Lesson[].class)));
          System.out.printf("총 %d개 수업정보를 로딩하였습니다.\n", lessonList.size());
        } catch (Exception e) {
          break;
        }
      }

    } catch (IOException e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    }
  }

  private static void saveLessonData() {
    File file = new File("./lesson.json");
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))){
      out.write(new Gson().toJson(lessonList));
      System.out.printf("총 %d개 수업정보를 저장하였습니다.\n", lessonList.size());

    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }

  private static void loadMemberData() {
    File file = new File("./member.json");

    try(BufferedReader in = new BufferedReader(new FileReader(file))) {
      while (true) {
        try {
          memberList.addAll(Arrays.asList(new Gson().fromJson(in, Member[].class)));
          System.out.printf("총 %d개 멤버정보를 로딩하였습니다.\n", memberList.size());
        } catch (Exception e) {
          break;
        }
      }
    } catch (IOException e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.json");
    try(BufferedWriter out = new BufferedWriter(new FileWriter(file))){
      out.write(new Gson().toJson(memberList));
      System.out.printf("총 %d개 수업정보를 저장하였습니다.\n", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }

  private static void loadBoardData() {
    File file = new File("./board.json");

    try(BufferedReader in = new BufferedReader(new FileReader(file));
        Scanner scanner = new Scanner(in)){

      while (true) {
        try {
          boardList.addAll(Arrays.asList(new Gson().fromJson(in, Board[].class)));
          System.out.printf("총 %d개 게시판 정보를 로딩하였습니다.\n", boardList.size());
        } catch (Exception e) {
          break;
        }
      }
    } catch (IOException e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    }
  }

  private static void saveBoardData() {
    File file = new File("./board.json");
    try(BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(boardList));
      System.out.printf("총 %d개 게시판 정보를 저장하였습니다.\n", boardList.size());
    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }



}
