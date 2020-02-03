package yk.learn.practicenote;

import java.util.Arrays;

public class Stack<E> implements Cloneable {
  private Object[] elementData;
  private int size;
  private final int DEFAULT_CAPACITY;
  
  public Stack() {
    DEFAULT_CAPACITY = 10;
    elementData = new Object[DEFAULT_CAPACITY];
    size = 0;
  }
  
  public void push(Object value) {
    if(this.size == elementData.length) {
      grow();
    }
    elementData[size++] = value;
  }
  
  private void grow() {
    elementData = Arrays.copyOf(elementData, newCapacity());
  }

  private int newCapacity() {
    int oldCapacity = this.elementData.length;
    return oldCapacity + (oldCapacity>>1);
  }

  public Object pop() {
    if(empty())
      return null;
    
    Object popValue = elementData[--size];
    elementData[size] = null;
    return popValue;
  }

  public Object peek() {
    return this.elementData[size-1];
  }
  
  public boolean empty() {
    return this.size == 0;
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
      
      // 이렇게는 안되는걸까?
      /*
      temp.elementData = this.elementData.clone();
      return temp;
      */
      
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }
}
