package yk.learn;

public class AbtInteger {
  public static void main(String[] args) {
    
    // String -> Integer로 만들기
    Integer i1 = Integer.valueOf("10");
    System.out.println("1st Value : " + i1);
    Integer i2 = Integer.valueOf('c');
    System.out.println("2nd Value : " + i2);
    System.out.println("============================");
    System.out.print("(integer value) 1st + 2nd = ");
    System.out.println(i1 + i2);
    
    // comparison (A>B:1 , A<B:-1 , A==B:0) 
    System.out.print("(comparision) ");
    System.out.println(i1.compareTo(i2));
    
    // Integer -> int로 만들기
    int a1 = i1.intValue();
    int a2 = i2.intValue();
    System.out.print("(int value) 1st + 2nd = ");
    System.out.println(a1 + a2);

    System.out.println("============================");
    // String -> int로 만들기
    //1
    int a3 = Integer.valueOf("12345").intValue()+1;
    System.out.println(a3);
    //2
    a3 = Integer.parseInt("12345");
    System.out.println(a3+1);
    
  }
}
