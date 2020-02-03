package yk.learn.practicenote;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LinkedList<E> {
  
  private Node<E> first;
  private Node<E> last;
  private int size;
  
  public void add(E value) {
    Node<E> newNode = new Node<>();
    newNode.value = value;
    
    if(first == null) {
      last = first = newNode;
    }
    else {
      last.next = newNode;
      last = newNode;
    }
    size++;
  }
  
  public E get(int index) {
    if(index < 0 || index >= this.size) {
      return null;
    }
    
    Node<E> cursor = first;
    for(int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    } return cursor.value;
  }
  
  public E set(int index, E value) {
    if(index < 0 || index >= this.size) {
      return null;
    }
    
    Node<E> cursor = first;
    for(int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    } 
    E oldValue = cursor.value;
    cursor.value = value;
    return oldValue;
  }
  
  public Object remove(int index) {
    if(index < 0 || index >= this.size) {
      return null;
    }
    
    Node<E> cursor = first;
    for(int i = 0 ; i < index-1 ; i++) {
      cursor = cursor.next;
    } 
    Node<E> deletedNode;
    
    if(index == 0) {
      deletedNode = first;
      first = first.next;
    }
    else {
      deletedNode = cursor.next;
      cursor.next = cursor.next.next;
    }
    size--;
    deletedNode.next = null;
    return deletedNode;
  }
  
  
  public void add(int index, E value) {
    if(index < 0 || index >= this.size) {
      return;
    }
    
    Node<E> newNode = new Node<>();
    newNode.value = value;
    
    Node<E> cursor = first;
    for(int i = 0 ; i < index-1 ; i++) {
      cursor = cursor.next;
    } 
    
    newNode.next = cursor.next;
    cursor.next = newNode;
    
    size++;
  }  
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] obj) {
    if(obj.length < this.size()) {
      obj = (E[])Array.newInstance(obj.getClass().getComponentType(), this.size);
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < this.size(); i++) {
      obj[i] = cursor.value;
      cursor = cursor.next;
    }
    return obj;
  }
  
  public int size() {
    return this.size;
  }
  
  class Node<T> {
    T value;
    Node<T> next;
  }
}
