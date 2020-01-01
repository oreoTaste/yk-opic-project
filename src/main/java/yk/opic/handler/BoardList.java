package yk.opic.handler;

import java.util.Arrays;
import yk.opic.domain.Board;

public class BoardList {
  static final int DEFAULT_CAPACITY = 2;  
  int size = 0;
  Board[] list;

  public BoardList() {
    list = new Board[DEFAULT_CAPACITY];
  }

  public BoardList(int capacity) {
    if(capacity < 0 || capacity > 100_000)
      list = new Board[DEFAULT_CAPACITY];
    else
      list = new Board[capacity];
  }
  
////////////////////////////////////////////////////////////////
  
  public void add(Board board) {
    int oldCapacity = this.list.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if(oldCapacity == size) {
      list = Arrays.copyOf(list, newCapacity);
    }
    this.list[this.size++] = board;
    System.out.println("저장하였습니다.");
  }
  
////////////////////////////////////////////////////////////////

  public Board[] toArray() {
    return Arrays.copyOf(list, size);
  }

  public Board detail(int detailNum) {
    for(int i = 0 ; i < this.size ; i++) {
      if(this.list[i].getNo() == detailNum) {
        return this.list[i];
      }
    }
    return null;
  }
}
