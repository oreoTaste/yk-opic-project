package yk.opic.util;

import java.util.Arrays;
import yk.opic.domain.Board;

public class ArrayList<E> extends AbstractList<E>{
  static final int DEFAULT_CAPACITY = 2;  
  int size = 0;
  Object[] elementData;

  public ArrayList() {
    elementData = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int capacity) {
    if(capacity < 0 || capacity > 100_000)
      elementData = new Object[DEFAULT_CAPACITY];
    else
      elementData = new Object[capacity];
  }

  ////////////////////////////////////////////////////////////////

  public void add(E arr) {
    if(this.size == this.elementData.length) {
      grow();
    }
    this.elementData[this.size++] = arr;
    System.out.println("저장하였습니다.");
  }

  
  private void grow() {
    elementData = Arrays.copyOf(elementData, newCapacity());
  }

  
  private int newCapacity() {
    int oldCapacity = this.elementData.length;
    return oldCapacity + (oldCapacity >> 1);
  }

  ////////////////////////////////////////////////////////////////

  @Override
  public void add(int index, E value) {
    
    if(index < 0 || index > this.size)
      return;
    
    if(this.size == this.elementData.length) {
      grow();
    }
    for(int i = size ; i > index ; i--) {
      this.elementData[i] = this.elementData[i-1];
    }
    this.elementData[index] = value;
    size++;
  }

  ////////////////////////////////////////////////////////////////

  @SuppressWarnings("unchecked")
  @Override
  public E[] toArray() {
    return (E[]) Arrays.copyOf(this.elementData, this.size);
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if(this.elementData.length < this.size) {
      return (E[])Arrays.copyOf(this.elementData, this.size, arr.getClass());
    }
    System.arraycopy(this.elementData, 0, arr, 0, this.size);
    return arr;
  }

  ////////////////////////////////////////////////////////////////

  @SuppressWarnings("unchecked")
  public E get(int idx) {
    if(idx >= 0 && idx < this.size) {
      return (E) this.elementData[idx];
    } else return null;
  }

  public int size() {
    return this.size;
  }


  @SuppressWarnings("unchecked")
  public E set(int idx, E arr) {
    E oldValue = (E)this.elementData[idx];
    if(idx < 0 && idx >= this.size) {
      return (E) this.elementData[idx];
    }
    this.elementData[idx] = arr;
    return oldValue;
  }

  @SuppressWarnings("unchecked")
  public E remove(int idx) {
    E oldValue = (E)this.elementData[idx];
    if(idx < 0 && idx >= this.size) {
      return (E) this.elementData[idx];
    }

    System.arraycopy(this.elementData, idx + 1, this.elementData, idx, this.size - idx - 1);
    this.size--;
    return oldValue;
  }




}





