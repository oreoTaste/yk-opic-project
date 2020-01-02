package yk.opic.handler;

import java.util.Arrays;
import yk.opic.domain.Board;

public class ArrayList {
  static final int DEFAULT_CAPACITY = 2;  
  int size = 0;
  Object[] list;

  public ArrayList() {
    list = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int capacity) {
    if(capacity < 0 || capacity > 100_000)
      list = new Object[DEFAULT_CAPACITY];
    else
      list = new Object[capacity];
  }

  ////////////////////////////////////////////////////////////////

  public void add(Object object) {
    int oldCapacity = this.list.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if(oldCapacity == size) {
      list = Arrays.copyOf(list, newCapacity);
    }
    this.list[this.size++] = object;
    System.out.println("저장하였습니다.");
  }

  ////////////////////////////////////////////////////////////////

  public Object[] toArray() {
    return Arrays.copyOf(list, size);
  }

  ////////////////////////////////////////////////////////////////
  
  public Object detail(int idx) {
    if(idx >= 0 && idx < this.size) {
      return this.list[idx];
    } else return null;
  }
}
