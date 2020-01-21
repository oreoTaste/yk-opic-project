// 메서드 레퍼런스 : static method reference
package yk.learn.practicenote;

public class Exam0540중요 {
  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
    public static int minus(int a, int b) {return a - b;}
    public static int multiple(int a, int b) {return a * b;}
    public static int divide(int a, int b) {return a / b;}
  }

  static interface Calculator {int compute(int a, int b);}
  static interface Calculator1 {int compute(int a, int b);}
  static interface Calculator2 {int compute(int a, int b);}
  static interface Calculator3 {int compute(int a, int b);}
  static interface Calculator4 {int compute(int a, int b);}
  static interface Calculator5 {int compute(int a, int b);}
  static interface Calculator6 {int compute(int a, int b);}
  static interface Calculator7 {int compute(Integer a, Integer b);}
  static interface Calculator8 {int compute(Integer a);}
  static interface Calculator9 {int compute(int a, int b, int c);}

  public static void main(String[] args) {
    Calculator1 c1 = MyCalculator::plus;
    // System.out.println(c1.compute(1, 2));

    Calculator2 c2 = MyCalculator::plus;
    // System.out.println(c2.compute(1, 2));

     Calculator3 c3 = MyCalculator::plus; System.out.println(c3.compute(1, 2));
     Calculator4 c4 = MyCalculator::plus; System.out.println(c4.compute(1, 2));
     Calculator5 c5 = MyCalculator::plus; System.out.println(c5.compute(1, 2));
     Calculator6 c6 = MyCalculator::plus; System.out.println(c6.compute(1, 2));
     Calculator7 c7 = MyCalculator::plus; System.out.println(c7.compute(1, 2));
     Calculator8 c8 = MyCalculator::plus; System.out.println(c8.compute(1));
     Calculator9 c9 = MyCalculator::plus; System.out.println(c9.compute(1, 2, 3));
     
  }
}


