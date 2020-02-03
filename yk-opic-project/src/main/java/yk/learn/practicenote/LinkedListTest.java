package yk.learn.practicenote;

public class LinkedListTest {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add("aaaa");
    list.add("bbbb");
    list.add("cccc");
    list.add("dddd");
    list.add("eeee");
    
    
    list.add(1,"wow");
    list.remove(3);
    System.out.println(list.get(0));
    list.set(0, "setted");
    
    print(list);
  }
  
  public static void print(LinkedList list) {
    String[] arr = (String[]) list.toArray(new String[0]);
    
    for(String a : arr) {
      System.out.println(a);
    }
  }
}
