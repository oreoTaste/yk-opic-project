package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Board;

public class BoardHandler {
  BoardList boardList;
  public Scanner input;

  public BoardHandler(Scanner input) {
    this.input = input;
    boardList = new BoardList();
  }

  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    boardList = new BoardList(capacity);
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

    boardList.add(board);
  }

  public void listBoard() {
    Board[] brd = boardList.toArray();
    for(Board b : brd) {
      System.out.printf("%d, %s, %s, %d\n", 
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    System.out.print("게시물 번호? ");
    int detailNum = input.nextInt();
    input.nextLine();
    Board board = new Board();
    board = boardList.detail(detailNum);
    if (board == null)
      System.out.println("게시물 번호가 유효하지 않습니다.");
    else {
      System.out.printf("번호 : %d\n", board.getNo());
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("등록일 : %tF\n", board.getDate());
      System.out.printf("주회수 : %d\n", board.getViewCount());
    }
  }
}
