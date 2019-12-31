package yk.opic;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Board;

public class BoardHandler {
  // 클래스 필드로 관리
  static final int SIZE = 100;
  public Scanner input;
  
  // 인스턴스 필드로 관리
  int boardsCount = 0;
  Board[] boards = new Board[SIZE];
  
  public BoardHandler(Scanner input) {
    this.input = input;
  }
  
  public void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.setNo(input.nextInt());
    input.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.setTitle(input.nextLine());
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    
    this.boards[this.boardsCount++] = board;
    System.out.println("저장하였습니다.");
  }
  
  public void listBoard() {
    for (int i = 0; i < this.boardsCount; i++) {
      Board board = this.boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          board.getNo(), board.getTitle(), board.getDate(), board.getViewCount());
    }
  }
}
