package yk.opic.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LinkedList<E> extends AbstractList<E>{

  Node<E> first;
  Node<E> last;
  int size;

  public LinkedList() {
    
  }
  
  public void add(E e) {
    Node<E> newNode = new Node<>();
    newNode.value = e;

    if(first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    size++;
  }

  public E get(int index) {
    if(index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    } return cursor.value;
  }

  public void add(int index, E obj) {
    if(index < 0 || index >= size) {
      return;
    }
    Node<E> newNode = new Node<>();
    newNode.value = obj;

    Node<E> cursor = first;
    for(int i = 0 ; i < index-1 ; i++) {
      cursor = cursor.next;
    }

    if(index == 0) {
      newNode.next = first;
      first = newNode;
    }
    else {
      newNode.next = cursor.next;
      cursor.next = newNode;
    }
    size++;
  }

  public E remove(int index) {
    if(index < 0 || index >= size) {
      return null;
    }

    Node<E> cursor = first;
    for(int i = 0 ; i < index-1 ; i++) {
      cursor = cursor.next;
    }

    Node<E> deleted = null;
    if(index == 0) {
      deleted = first;
      first = deleted.next;
    }
    else {
      deleted = cursor.next;
      cursor.next = deleted.next;
    }
    deleted.next = null;
    size--;
    return deleted.value;
  }

  public E set(int index, E obj) {
    if(index < 0 || index >= size) {
      return null;
    }

    Node<E> cursor = first;
    for(int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    }
    E oldValue = cursor.value;
    cursor.value = obj;
    return oldValue;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E[] toArray() {
    Object[] arr = new Object[this.size];
    Node<E> cursor = first;
    for(int i = 0 ; i <this.size ; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return (E[]) arr;
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] e) {
    if(e.length < this.size) {
      e = (E[])Array.newInstance(e.getClass().getComponentType(), this.size);
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < size ; i++) {
      e[i] = cursor.value;
      cursor = cursor.next;
    } return e;
  }
  
  public int size() {
    return this.size;
  }

  class Node<T> {
    T value;
    Node<T> next;
  }

}
