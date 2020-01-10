package yk.learn.practicenote;

public class LinkedLIstTest {
  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    
    list.add("0aaaa");
    list.add("1aaaa");
    list.add("2aaaa");
    list.add("3aaaa");
    
    list.add(0,"first");
    list.remove(1);
    list.set(2, "second line");
    print(list);
    
  }
  
  static void print(LinkedList<String> list) {
    
    String[] arr = new String[list.size()];
    list.toArray(arr);
    for(String value : arr) {
      System.out.println(value);
    }
  }
}
