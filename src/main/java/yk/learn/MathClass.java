package yk.learn;

public class MathClass {
  public static void main(StringClass[] args) {
    // Most methods in Math Class is static.
    final float PI = (float) Math.PI;
    System.out.println("Value Of PI : " + PI);
    
    System.out.println("* (floored) : " + Math.floor(PI));
    System.out.println("* (ceiled) : " + Math.ceil(PI));
    
    float minus = -PI;
    System.out.println("* (abs on minus) : " + Math.abs(minus));
    System.out.println("* (round of PI)" + Math.round(PI));
    System.out.println("* (round of minus PI) : " + Math.round(minus));
    
    System.out.println("* (pow(2, 3)) : " + Math.pow(2, 3));
    System.out.println("* (sqrt of 16) : " + Math.sqrt(16));
  }
}
