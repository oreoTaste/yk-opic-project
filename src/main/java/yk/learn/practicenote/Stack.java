package yk.learn.practicenote;

import java.util.Arrays;

public class Stack<E> implements Cloneable{
  private Object[] elementData;
  private final int DEFAULT_CAPACITY;
  int size;

  public Stack() {
    DEFAULT_CAPACITY = 10;
    this.elementData = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    if(empty())
      return null;
    E popValue = (E) this.elementData[--size];
    this.elementData[size] = null;
    return popValue;
  }


  public void push(E value) {
    if(this.size == elementData.length) {
      grow();
    }

    this.elementData[size] = value;
    size++;
  }
  
  
  public boolean empty() {
    return this.size == 0;
  }


  @SuppressWarnings("unchecked")
  public E peek() {
    return (E) this.elementData[size-1];
  }


  private void grow() {
    elementData = Arrays.copyOf(elementData, newCapacity());
  }

  private int newCapacity() {
    int oldCapacity = elementData.length;
    return oldCapacity + (oldCapacity>>1);
  }

  /*
  @Override
  public Stack clone() {
    try {
    return (Stack) super.clone();
    } catch(CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }
  }
   */

  
  // deep-clone방식
  @SuppressWarnings("unchecked")
  public Stack<E> clone() {
    try {
      Stack<E> temp = (Stack<E>) super.clone();

      Object[] arr = new Object[this.size];
      for(int i = 0 ; i < this.size ; i++) {
        arr[i] = this.elementData[i];
      }

      temp.elementData = arr;

      return temp;
    } catch(CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }

  }
}
