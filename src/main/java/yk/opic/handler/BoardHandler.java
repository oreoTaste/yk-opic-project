package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Board;
import yk.opic.domain.Board;
import yk.opic.domain.Board;
import yk.opic.domain.Board;
import yk.opic.util.ArrayList;
import yk.opic.util.LinkedList;
import yk.opic.util.Prompt;

public class BoardHandler {
  LinkedList<Board> boardList;
  public Scanner input;
  Prompt prompt;

  public BoardHandler(Prompt prompt) {
    this.prompt = prompt;
    boardList = new LinkedList<>();
  }


  public void addBoard() {

    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
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

    int no = prompt.inputInt("번호? ");
    int index = indexOfBoard(no);

    if(index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    Board board = this.boardList.get(index);

    try{
      System.out.printf("번호 : %d\n", board.getNo());
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("등록일 : %1$tF %1$tH:%1$tM:%1$tS\n", board.getDate());
      System.out.printf("조회수 : %d\n", board.getViewCount());
    } catch (Exception e){
      return;
    } finally{ } 
  }


  public void updateBoard() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfBoard(no);

    if(index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(oldBoard.getNo());

    newBoard.setTitle(prompt.inputString(
        String.format("제목? (%s) ", oldBoard.getTitle()),
        oldBoard.getTitle()));

    newBoard.setDate(new Date(System.currentTimeMillis()));

    newBoard.setViewCount(0);

    if(newBoard.equals(oldBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
    } else {
      this.boardList.set(index, newBoard);
      System.out.println("게시글을 변경했습니다.");
    }
  }


  public void deleteBoard() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfBoard(no);

    if(index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    this.boardList.remove(index);
    System.out.println("게시글을 삭제했습니다.");
  }


  public int indexOfBoard(int no) {
    for(int i = 0 ; i < this.boardList.size() ; i++) {
      if(boardList.get(i).getNo() == no)
        return i;
    } return -1;
  }

}
