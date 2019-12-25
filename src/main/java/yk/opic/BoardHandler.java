package yk.opic;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Board;

public class BoardHandler {
  // 클래스 필드로 관리
  static final int SIZE = 100;
  static Scanner scanner;
  
  // 인스턴스 필드로 관리
  int boardsCount = 0;
  Board[] boards = new Board[SIZE];
  
  static void addBoard(BoardHandler bh) {
    Board board = new Board();
    System.out.print("번호? ");
    board.no = scanner.nextInt();
    scanner.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.title = scanner.nextLine();
    board.date = new Date(System.currentTimeMillis());
    board.viewCount = 0;
    
    bh.boards[bh.boardsCount++] = board;
    System.out.println("저장하였습니다.");
  }
  
  static void listBoard(BoardHandler bh) {
    for (int i = 0; i < bh.boardsCount; i++) {
      Board board = bh.boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          board.no, board.title, board.date, board.viewCount);
    }
  }
}
