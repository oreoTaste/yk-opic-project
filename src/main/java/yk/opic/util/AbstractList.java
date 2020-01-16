package yk.opic.util;


public abstract class AbstractList<E> implements List<E>{
  
  protected int size;
  
  public int size() {
    return size;
  }
  
  @Override
  public Iterator<E> iterator() {
    return this.new ListIterator<E>();
  }
  
  // non-static nested class (inner class)
  class ListIterator<T> implements Iterator<T> {
    
    List<T> list;
    int cursor;
    
    @SuppressWarnings("unchecked")
    public ListIterator() {
      this.list = (List<T>) AbstractList.this;
    }
    
    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }
    
    @Override
    public T next() {
      return list.get(cursor++);
    }
  }
}
