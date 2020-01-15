package yk.opic.util;

public interface List<E> {
  
  int size();
  
  void add(E e);
  
  void add(int index, E value);
  
  E get(int index);
  
  E set(int index, E e);
  
  E remove(int index);
  
  E[] toArray();
  
  E[] toArray(E[] arr);
  
}
