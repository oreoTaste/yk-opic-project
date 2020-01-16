package yk.opic.util;

import java.util.Arrays;

public class Stack<E> implements Cloneable {
  private final int DEFAULT_CAPACITY;
  private Object[] elementData;
  private int size;

  public Stack() {
    DEFAULT_CAPACITY = 100;
    elementData = new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  public void push(Object value) {
    if(this.elementData.length == size)
      grow();
    elementData[size++] = value;
  }

  private void grow() {
    elementData = Arrays.copyOf(elementData, newCapaicity());
  }

  private int newCapaicity() {
    int oldCapacity = this.elementData.length;
    return oldCapacity + (oldCapacity>>1);
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    if(empty()) {
      return null;
    }
    E popData = (E) elementData[--size];
    elementData[size] = null;
    return popData;
  }

  public boolean empty() {
    return this.size == 0;
  }

  public Object peek() {
    return this.elementData[size-1];
  }

  public int size() {
    return this.size;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Stack<E> clone() {
    try {
      Stack<E> temp = (Stack<E>) super.clone();
      Object[] arr = new Object[this.size];
      for (int i = 0; i < this.size; i++) {
        arr[i] = this.elementData[i];
      }
      temp.elementData = arr;
      return temp;
    } catch (CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }
  }

  public Iterator<E> iterator() {
    // 로컬클래스로 유지보수 편의
    return new Iterator<E>(){
      
      Stack<E> stack;
      
      { //익명클래스는 이름이 없어서, 생성자를 생성할 수 없다.
        this.stack = (Stack<E>) Stack.this.clone();
      }

      @Override
      public boolean hasNext() {
        return !stack.empty();
      }

      @Override
      public E next() {
        return stack.pop();
      }
    };
  }

  
}
