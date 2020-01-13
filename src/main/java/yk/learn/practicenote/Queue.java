package yk.learn.practicenote;

public class Queue <E> extends LinkedList<Object> implements Cloneable{

  public void offer(E object) {
    this.add(object);
  }

  public E poll() {
    return (E) this.remove(0);
  }


  /*
 @Override
  public Queue clone() {
   try {
    return (Queue) super.clone(); // shallow- copy.
   } catch (CloneNotSupportedException e) {
     System.out.println(e);
     return null;
   }
  }
   */

  @SuppressWarnings("unchecked")
  public Queue<E> clone() {
    //deep copy 시전!
    Queue<E> temp = new Queue<>();
    
    for(int i = 0 ; i <= this.size(); i++) {
      temp.offer((E) this.get(i));
    }
    return temp;
  }

}
