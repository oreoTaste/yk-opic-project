package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Board;
import yk.opic.domain.Board;
import yk.opic.util.ArrayList;

public class BoardHandler {
  ArrayList<Board> boardList;
  public Scanner input;

  public BoardHandler(Scanner input) {
    this.input = input;
    boardList = new ArrayList<>();
  }

  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    boardList = new ArrayList<>(capacity);
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
    Board[] board = new Board[this.boardList.size()];
    boardList.toArray(board);
    for(Board b : board) {
      System.out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n", 
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Board board = this.boardList.get(tempIndex);
    if(board == null) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }
    else {
      System.out.printf("번호 : %d\n", board.getNo());
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("등록일 : %1$tF %1$tH:%1$tM:%1$tS\n", board.getDate());
      System.out.printf("조회수 : %d\n", board.getViewCount());
    }
  }


  public void updateBoard() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Board oldValue = this.boardList.get(tempIndex);
    if(oldValue == null) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    Board newValue = new Board();
    boolean changed = true;

    System.out.printf("번호? %d\n", oldValue.getNo());
    newValue.setNo(oldValue.getNo());

    System.out.printf("제목? (%s) ", oldValue.getTitle());
    String tempTitle= input.nextLine();
    if(tempTitle.length() == 0) {
      changed = false;
      newValue.setTitle(oldValue.getTitle());
    }
    newValue.setTitle(tempTitle);

    newValue.setDate(new Date(System.currentTimeMillis()));
    newValue.setViewCount(0);

    if(changed) {
      this.boardList.set(tempIndex, newValue);
      System.out.println("게시글을 변경했습니다.");
    }
    else System.out.println("게시글 변경을 취소했습니다.");

  }

  public void deleteBoard() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Board board = this.boardList.get(tempIndex);
    if(board == null) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    this.boardList.remove(tempIndex);
    System.out.println("게시글을 삭제했습니다.");
  }

}
