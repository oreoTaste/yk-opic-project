package yk.sideProject;

import java.util.Scanner;

//mini-project) make a factorial calculator using recursive function 
//과제: 재귀호출을 이용하여 n! 을 계산하라.
//실행)
//입력? 5
//5! = 5 * 4 * 3 * 2 * 1 = 120

public class FactorialRecursive {
  public static void main(String[] args) {
    // 사용자로부터 정수 값을 입력 받는다.
    Scanner scanner = new Scanner(System.in);
    System.out.println("과제: 재귀호출을 이용하여 n! 을 계산하라.");
    System.out.println("실행) ");
    System.out.print("입력? ");

    int n = scanner.nextInt();
    scanner.close();
    System.out.printf("%d! = ", n);
    // n! 을 계산한다.

    int result = factorial(n);
    System.out.println(result);
    // 결과 값을 출력한다.
  }

  static int factorial(int n) {
    // 코드를 완성하시오!
    if(n == 1) {
      System.out.print(" 1 = ");
      return 1;
    } 
    else {
      System.out.printf(" %d *", n);
      return factorial(n-1) * n;
    }
  }
}

