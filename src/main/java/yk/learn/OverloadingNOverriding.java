package yk.learn;

class A2 {
  int a = 10;
  void print() {
    System.out.println("A : " + a);
  }
  //@Identifier (print)
  void print(int overloading) {
    this.a = overloading;
    System.out.println("A : " + overloading);
  }
  
  public boolean equals(Object object) {
    if(object.getClass() != A2.class) return false;
    if(this.a != ((A2)object).a) return false;
    return true;
  }
}

class B2 extends A2 {
  int b = 20;
  @Override
  void print() {
    System.out.println("B : " + a);
  }
}

public class OverloadingNOverriding {
  public static void main(String[] args) {
    A2 a2 = new A2();
    B2 b2 = new B2();
    
    // basic
    a2.print();
    // overloading  :
    // 기존의 메서드에서 method signature을 (파라미터를) 수정하는 방식으로 
    // 수정하여 여러 메서드를 만든다

    a2.print(100);
    // @Override (덮어쓰기, 재정의): 
    // 기존과 파라미터까지 모든 method signature가 동일하게 메서드를 변형.
    // 상속받은 클래스는 상속받은 메서드를 변형하여 메서드를 만든다.
    b2.print();
    
    
    // Override Method of "equals"
    System.out.println();
    A2 a3 = new A2();
    System.out.println("(((((Override Method of Equals)))))");
    System.out.println(a3.equals(b2));
    System.out.println(a3.equals(new A2()));
  }
}
