package yk.learn.practicenote;

import java.util.Iterator;

public class IteratorTest {
  public static void main(final String[] args) {

    final MyDataType m = new MyDataType(new String[] {"첫번째", "두번째", "세번째", "네번째"});
    final Iterator i = m.iterator();
    while (i.hasNext()) {
      System.out.println(i.next());
    }
  }
}


class MyDataType {
  String[] str;

  MyDataType(final String... str) {
    this.str = str;
  }

  public String getElement(final int index) {
    return str[index];
  }

  public Iterator iterator() {
    return new Iterator() {
      MyDataType m;
      int cursor;

      {
        m = MyDataType.this;
      }

      @Override
      public boolean hasNext() {
        return cursor < m.size();
      }

      @Override
      public Object next() {
        return m.str[cursor++];
      }

    };
  }

  public int size() {
    return str.length;
  }


}
