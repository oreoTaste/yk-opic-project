package yk.learn.practicenote;

public class StackTest {
  public static void main(String[] args) {
    Stack stack = new Stack();
    stack.push("aaaa");
    stack.push("bbbb");
    stack.push("cccc");
    stack.push("dddd");
    stack.push("eeee");
    
    stack.pop();
    System.out.println(stack.peek());
    print(stack);
  }
  
  private static void print(Stack stack) {
    while(!stack.empty()) {
      System.out.println(stack.pop());
    } 
  }
}
