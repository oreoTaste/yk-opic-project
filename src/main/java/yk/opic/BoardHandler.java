package yk.opic;

import java.sql.Date;
import java.util.Scanner;

public class BoardHandler {

  static class Board {
    int no;
    String title;
    Date date;
    int viewCount;
  }
  static final int SIZE = 100;
  static int boardsCount = 0;
  static Board[] boards = new Board[SIZE];
  static Scanner scanner;

  static void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.no = scanner.nextInt();
    scanner.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.title = scanner.nextLine();
    board.date = new Date(System.currentTimeMillis());
    board.viewCount    = 0;

    boards[boardsCount++] = board;
    System.out.println("저장하였습니다.");
  }
  
  static void listBoard() {
    for (int i = 0; i < boardsCount; i++) {
      Board brd = boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          brd.no, brd.title, brd.date, brd.viewCount);
    }
  }
}
