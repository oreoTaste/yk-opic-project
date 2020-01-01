package yk.opic.handler;

import java.util.Arrays;
import yk.opic.domain.Lesson;

public class LessonList {
  Lesson[] list;
  int size = 0;
  static final int DEFAULT_CAPACITY = 2;

  public LessonList() {
    list = new Lesson[DEFAULT_CAPACITY];
  }

  public LessonList(int capacity) {
    if(capacity < 0 || capacity > 100_000)
      list = new Lesson[DEFAULT_CAPACITY];
    else
      list = new Lesson[capacity];
  }

  //////////////////////////////////////////////////////////////////////

  public void add(Lesson les) {
    int oldCapacity = this.list.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if(oldCapacity == size) {
      list = Arrays.copyOf(list, newCapacity);
    }
    this.list[this.size++] = les;
  }

  //////////////////////////////////////////////////////////////////////

  public Lesson[] toArray() {
    return Arrays.copyOf(list, size);
  }  
}
