package yk.opic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
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
    File file = new File("./lesson.csv");
    FileReader in = null;
    Scanner scanner = null;

    try {
      int count = 0;
      in = new FileReader(file);
      scanner = new Scanner(in);

      while (true) {
        try {
          String line = scanner.nextLine();
          String[] data = line.split(",");

          Lesson lesson = new Lesson();
          lesson.setNo(Integer.parseInt(data[0]));
          lesson.setTitle(data[1]);
          lesson.setContext(data[2]);
          lesson.setStartDate(Date.valueOf(data[3]));
          lesson.setEndDate(Date.valueOf(data[4]));
          lesson.setTotalHour(Integer.parseInt(data[5]));
          lesson.setDailyHour(Integer.parseInt(data[6]));

          lessonList.add(lesson);
          count++;
        } catch (NumberFormatException | NoSuchElementException e) {
          break;
        }
      }
      System.out.printf("총 %d개 수업정보를 로딩하였습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    } finally {
      try {
        scanner.close();
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveLessonData() {
    File file = new File("./lesson.csv");
    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Lesson lesson : lessonList) {
        String line = String.format("%d,%s,%s,%s,%s,%d,%d\n", lesson.getNo(), lesson.getTitle(),
            lesson.getContext(), lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHour(),
            lesson.getDailyHour());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d개 수업정보를 저장하였습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException | NullPointerException e) {
      }
    }
  }

  private static void loadMemberData() {
    File file = new File("./member.csv");
    FileReader in = null;
    Scanner scanner = null;
    int count = 0;

    try {
      in = new FileReader(file);
      scanner = new Scanner(in);

      while (true) {
        try {
          String line = scanner.nextLine();
          String[] data = line.split(",");

          Member member = new Member();
          member.setNo(Integer.parseInt(data[0]));
          member.setName(data[1]);
          member.setEmail(data[2]);
          member.setPassword(data[3]);
          member.setPhoto(data[4]);
          member.setTel(data[5]);
          member.setRegisteredDate(Date.valueOf(data[6]));

          memberList.add(member);
          count++;
        } catch (NumberFormatException | NoSuchElementException e) {
          break;
        }
      }
      System.out.printf("총 %d개 멤버정보를 로딩하였습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    } finally {
      try {
        scanner.close();
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.csv");
    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Member member : memberList) {
        String line = String.format("%d,%s,%s,%s,%s,%s,%s\n", member.getNo(), member.getName(),
            member.getEmail(), member.getPassword(), member.getPhoto(), member.getTel(),
            member.getRegisteredDate());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d개 수업정보를 저장하였습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException | NullPointerException e) {
      }
    }
  }

  private static void loadBoardData() {
    File file = new File("./board.csv");
    FileReader in = null;
    Scanner scanner = null;

    try {
      in = new FileReader(file);
      scanner = new Scanner(in);
      int count = 0;

      while (true) {
        try {
          String line = scanner.nextLine();
          String[] data = line.split(",");

          Board board = new Board();
          board.setNo(Integer.parseInt(data[0]));
          board.setTitle(data[0]);
          board.setDate(Date.valueOf(data[0]));
          board.setViewCount(Integer.valueOf(data[0]));

          boardList.add(board);
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d개 멤버정보를 로딩하였습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 로딩 오류 : " + e.getMessage());
    } finally {
      try {
        scanner.close();
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveBoardData() {
    File file = new File("./board.csv");
    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Board board : boardList) {
        String line = String.format("%d,%s,%s,%d\n", board.getNo(), board.getTitle(),
            board.getDate(), board.getViewCount());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d개 게시판 정보를 저장하였습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException | NullPointerException e) {
      }
    }
  }



}
