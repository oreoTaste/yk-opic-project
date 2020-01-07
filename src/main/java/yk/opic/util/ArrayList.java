package yk.opic.util;

import java.util.Arrays;
import yk.opic.domain.Board;

public class ArrayList<E> {
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

  public void add(E arr) {
    int oldCapacity = this.list.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if(oldCapacity == size) {
      list = Arrays.copyOf(list, newCapacity);
    }
    this.list[this.size++] = arr;
    System.out.println("저장하였습니다.");
  }

  ////////////////////////////////////////////////////////////////

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if(this.list.length < this.size) {
      return (E[])Arrays.copyOf(this.list, this.size, arr.getClass());
    }
    System.arraycopy(this.list, 0, arr, 0, this.size);
    return arr;
  }

  ////////////////////////////////////////////////////////////////
  
  @SuppressWarnings("unchecked")
  public E detail(int idx) {
    if(idx >= 0 && idx < this.size) {
      return (E) this.list[idx];
    } else return null;
  }

  public int size() {
    return this.size;
  }
}
