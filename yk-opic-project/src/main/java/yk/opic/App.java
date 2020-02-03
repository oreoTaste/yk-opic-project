package yk.opic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
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
    File file = new File("./lesson.data");

    try (DataInputStream in = new DataInputStream(
        new BufferedInputStream(new FileInputStream(file)))){
      while (true) {
        try {
          int size = in.readInt();
          for(int i = 0; i < size; i++) {
            Lesson lesson = new Lesson();
            lesson.setNo(in.readInt());
            lesson.setTitle(in.readUTF());
            lesson.setContext(in.readUTF());
            lesson.setStartDate(Date.valueOf(in.readUTF()));
            lesson.setEndDate(Date.valueOf(in.readUTF()));
            lesson.setTotalHour(in.readInt());
            lesson.setDailyHour(in.readInt());
            lessonList.add(lesson);
          }

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
    File file = new File("./lesson.data");
    try (DataOutputStream out = new DataOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))){

      out.writeInt(lessonList.size());
      for(Lesson lesson : lessonList) {
        out.writeInt(lesson.getNo());
        out.writeUTF(lesson.getTitle());
        out.writeUTF(lesson.getContext());
        out.writeUTF(lesson.getStartDate().toString());
        out.writeUTF(lesson.getEndDate().toString());
        out.writeInt(lesson.getTotalHour());
        out.writeInt(lesson.getDailyHour());
      }
      System.out.printf("총 %d개 수업정보를 저장하였습니다.\n", lessonList.size());

    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }

  private static void loadMemberData() {
    File file = new File("./member.data");
    try (DataInputStream in = new DataInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      int size = in.readInt();
      for(int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setPassword(in.readUTF());
        member.setPhoto(in.readUTF());
        member.setTel(in.readUTF());
        member.setRegisteredDate(Date.valueOf(in.readUTF()));
        memberList.add(member);
      }
      System.out.printf("총 %d개 멤버 로딩하였습니다.\n", memberList.size());

    } catch(Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.data");
    try(DataOutputStream out = new DataOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))){

      out.writeInt(memberList.size());
      for(Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getPassword());
        out.writeUTF(member.getPhoto());
        out.writeUTF(member.getTel());
        out.writeUTF(member.getRegisteredDate().toString());
      }

      System.out.printf("총 %d개 멤버정보를 저장하였습니다.\n", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 저장 오류 : \n" + e.getMessage());
    }
  }

  private static void loadBoardData() {
    File file = new File("./board.data");

    try (DataInputStream in = new DataInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      int size = in.readInt();
      for(int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
        board.setDate(Date.valueOf(in.readUTF()));
        board.setViewCount(in.readInt());
        boardList.add(board);
      }

      System.out.printf("총 %d개 게시글 로딩하였습니다.\n", boardList.size());

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private static void saveBoardData() {
    File file = new File("./board.data");

    try (DataOutputStream out = new DataOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeInt(boardList.size());
      for(Board board : boardList) {
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getDate().toString());
        out.writeInt(board.getViewCount());

      }

      System.out.printf("총 %d개 게시글 저장하였습니다.\n", boardList.size());
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }



}
