package yk.learn.practicenote;

import yk.learn.practicenote.LinkedList;

public class Queue extends LinkedList<String>{

  public void offer(String value) {
    this.add(value);
  }

  public void poll() {
    this.remove(0);
  }

  public Object clone() {
    try {
      Queue temp = (Queue) super.clone();

      for(int i = 0 ; i<=this.size() ; i++) {
        temp.offer(this.get(i));
      }
      return temp;

    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }

}
