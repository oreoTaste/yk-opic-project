package yk.learn.practicenote;

class StackNQueue {
  public static void main(String[] args) {
    Queue queue = new Queue();
    System.out.println(queue.isEmpty());
    
    queue.enqueue("A1");
    queue.enqueue("B1");
    queue.enqueue("C1");
    queue.enqueue("C2");
    queue.enqueue("C3");
    
    queue.dequeue();
    
    queue.print();
  }
}


class Node {
  Object element;
  Node next;
  
  Node(Object element) {
    this.element = element;
    this.next = null;
  }
}


class Queue {
  Node front;
  Node rear;
  
  Queue() {
    this.front = null;
    this.rear = null;
  }
  
  public boolean isEmpty() {
    return this.front == null;
  }
  
  //enqueue 메소드 작성
  public void enqueue(Object element) {
    Node newNode = new Node(element);
    if(isEmpty()) {
      rear = front = newNode;
    }
    else {
      rear.next = newNode;
      rear = newNode;
    }
  }
  
  //dequeue 메소드 작성
  public Object dequeue() {
    Object element = peek();
    if(element == null) {
      return null;
    }
    else {
      Node deleted = front;
      front = front.next;
      return deleted.element;
    }
  }
  
  Object peek() {
    if(isEmpty()) {
      System.out.println("큐가 비어있습니다.");
      return null;
    }
    return front.element;
  }
  
  void print() {
    Node curr = front;
    while(curr != null) {
      System.out.println(curr.element);
      curr = curr.next;
    }
  }
  
}


class Stack {
  Node top;
  
  Stack() {
    this.top = null;
  }
  
  Object pop() {
    if(peek() == null)
      return null;
    this.top = this.top.next;
    return peek();
  }

  boolean isEmpty() {
    return this.top == null;
  }
  
  void push(Object element) {
    Node newNode = new Node(element);
    newNode.next = top;
    top = newNode;
  }
  
  Object peek() {
    if(isEmpty()) {
      System.out.println("스택이 비어있습니다.");
      return null;
    }
    return top.element;
  }

  void print() {
    Node curr = top;
    while(curr != null) {
      System.out.println(curr.element);
      curr = curr.next;
    }
  }
}