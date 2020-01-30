// 메서드 레퍼런스 : method reference
package yk.learn.practicenote;

import java.util.ArrayList;
import java.util.List;

public class MethodReference {

  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
  }

  interface Calculator1 {int compute(byte a, byte b);}
  interface Calculator2 {int compute(char a, char b);}
  interface Calculator3 {int compute(long a, long b);}
  interface Calculator4 {int compute(Integer a, Integer b);}
  interface Calculator5 {byte compute(int a, int b);}
  interface Calculator6 {char compute(int a, int b);}
  interface Calculator7 {long compute(int a, int b);}
  interface Calculator8 {int compute(Integer a);}
  interface Calculator9 {int compute(int a, int b, int c);}

  interface ListFactory {
    List create();
  }
  public static void main(String[] args) {
    /* 1) Parameter
     * interface : low-level (byte)
     * method    : high-level(long)
     * 그러나, method는 사용불가.
     * (int <-> Integer는 가능)
     */
    // int byte byte != int int int
    Calculator1 c1 = MyCalculator::plus;
    // System.out.println(c1.compute(1, 2));

    // int char char != int int int
    Calculator2 c2 = MyCalculator::plus;
    // System.out.println(c2.compute(1, 2));

    // int long long != int int int
    //Calculator3 c3 = MyCalculator::plus; System.out.println(c3.compute(1, 2));

    // int Integer Integer != int int int
    Calculator4 c4 = MyCalculator::plus;
    int i;
    System.out.println("the result of c4 (int) : " + (i = c4.compute(1, 2)));

    /* 2) return값
     *   interface : high-level(long)
     *   method    : low-level (int)
     */
    // byte int int != int int int
    // Calculator5 c5 = MyCalculator::plus; System.out.println(c5.compute(1, 2));

    // char int int != int int int
    // Calculator6 c6 = MyCalculator::plus; System.out.println(c6.compute(1, 2));

    // long int int == int int int
    long l;
    Calculator7 c7 = MyCalculator::plus;
    System.out.println("the result of c7 (long) : " + (l = c7.compute(1, 2)));

    // int Integer boolean != int int int
    //Calculator8 c8 = MyCalculator::plus; System.out.println(c8.compute(1));

    // int int int != int int int int
    //Calculator9 c9 = MyCalculator::plus; System.out.println(c9.compute(1, 2, 3));


    System.out.println("============================");

    ListFactory f1 = ArrayList::new;
    List list = f1.create();
    System.out.println(list instanceof ArrayList);
    System.out.println(list.getClass().getName());
  }


}


