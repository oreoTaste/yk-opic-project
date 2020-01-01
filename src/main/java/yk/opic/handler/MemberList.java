package yk.opic.handler;

import java.util.Arrays;
import yk.opic.domain.Member;

public class MemberList {
  int size = 0;
  Member[] list;
  static final int DEFAULT_CAPACITY = 2;
  
  public MemberList() {
    list = new Member[DEFAULT_CAPACITY];
  }
  
  public MemberList(int capacity) {
    if(capacity < 0 || capacity > 100_000)
    list = new Member[DEFAULT_CAPACITY];
    else
      list = new Member[capacity];
  }

  public Member[] toArray() {
    return Arrays.copyOf(list, size);
  }

  public void add(Member mem) {
    int oldCapacity = this.list.length;
    int newCapacity = oldCapacity + (oldCapacity >>1);
    if(oldCapacity == size) {
      list = Arrays.copyOf(list, newCapacity);
    }
    this.list[this.size++] = mem;
  }  
  
}
