package yk.learn;

class A1 {
  int a = 1;
  A1() {
    // new명령어로 instance생성시 출력
    System.out.println("class A :" + a);
  }
}

class B1 extends A1{
  int b = 1;
  B1() {
    // new명령어로 instance생성시 출력
    System.out.println("class B :" + b);
  }
}

public class GetClassNInstanceof {
  public static void main(StringClass[] args) {
    System.out.println("*** ObjA 생성 ***");
    A1 objA = new A1();
    System.out.println("*** ObjB 생성 ***");
    B1 objB = new B1();
    
    System.out.println();
    System.out.println("the Use of instanceof");
    if(objA instanceof A1 ) {
      System.out.println("objA <=(instance)=> A");
      // A는 A의 인스턴스이거나 자손이다.(child class, sub class)
    }
    if(objA instanceof B1 ) {
      System.out.println("objA <=(instance)=> B");
      // B는 A의 인스턴스이거나 자손이다.(child class, sub class)
    }

    if(objB instanceof A1 ) {
      System.out.println("objB <=(instance)=> A");
      // B는 A의 인스턴스이거나 자손이다.(child class, sub class)
    }

    if(objB instanceof B1 ) {
      System.out.println("objB <=(instance)=> B");
      // B는 B의 인스턴스이거나 자손이다.(child class, sub class)
    }
    
    System.out.println();
    System.out.println("the Use of instanceof");
    if(objA.getClass() == A1.class) {
      System.out.println("objA <=(getClass)=> A");
    }
    if(objA.getClass() == B1.class) {
      System.out.println("objA <=(getClass)=> B");
    }
    /*
    if(objB.getClass() == A.class) {
      System.out.println("objB <=(getClass)=> A");
    }
    */
    if(objB.getClass() == B1.class) {
      System.out.println("objB <=(getClass)=> B");
    }
  }
}
