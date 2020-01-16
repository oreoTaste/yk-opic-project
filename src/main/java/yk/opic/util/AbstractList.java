package yk.opic.util;


public abstract class AbstractList<E> implements List<E>{

  protected int size;

  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    // anonymous class
    return new Iterator<E>() {

      List<E> list;
      int cursor;

      // 익명클래스는 생성자를 만들 수 없기 때문에
      // instance field를 초기화 시키기 위해서는
      // 인스턴스 블럭을 사용해야한다.
      {
        this.list = (List<E>) AbstractList.this;
      }

      @Override
      public boolean hasNext() {
        return cursor < list.size();
      }

      @Override
      public E next() {
        return list.get(cursor++);
      }
    };
  }
}
