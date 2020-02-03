package yk.learn.practicenote;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ListOverall {
  public static void main(String[] args) {

    // ArrayList : add, get
    System.out.println("<<ArrayList : add & get>>");
    ArrayList arrayList = new ArrayList();
    arrayList.add("FirstArrayList");
    arrayList.add("SecondArrayList");
    arrayList.add("ThirdArrayList");

    System.out.println(arrayList.get(0));

    for (Object obj : arrayList.toArray())
      System.out.println(obj);

    System.out.println("=========================");
    // Stack : push, pop
    System.out.println("<<Stack : push & pop>>");
    Stack stack = new Stack();
    stack.push("FirstStack");
    stack.push("SecondStack");
    stack.push("ThirdStack");
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());

    System.out.println("=========================");
    // Queue : add, poll
    System.out.println("<<Queue : add & poll>>");
    Queue queue = new LinkedList();
    queue.add("FirstQueue");
    queue.add("SecondQueue");
    queue.add("ThirdQueue");
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());

    System.out.println("=========================");
    // HashSet : add, toArray
    System.out.println("<<HashSet : add, toArray>>");
    HashSet hashset = new HashSet();
    hashset.add("FirstHashSet");
    hashset.add("SecondHashSet");
    hashset.add("ThirdHashSet");
    for (Object obj : hashset.toArray())
      System.out.println(obj);
  }
}
