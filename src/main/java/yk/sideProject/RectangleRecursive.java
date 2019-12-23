package yk.sideProject;

import java.util.Scanner;

//mini-project) draw a rectangle using recursive function 
//과제: 재귀호출을 이용하여 직삼각형을 출력하라.
//실행)
//밑변의 길이? 5
//*
//**
//***
//****
//*****


public class RectangleRecursive {
  public static void main(String[] args) {
    // 사용자로부터 밑변의 길이를 입력 받는다.
    Scanner scanner= new Scanner(System.in);
    System.out.println("과제: 재귀호출을 이용하여 직삼각형을 출력하라.");
    System.out.println("실행) ");
    System.out.print("밑변의 길이? ");
    int base = scanner.nextInt();
    scanner.close();

    // 직삼각형을 출력한다.
    printTriangle(base);
  }

  static void printTriangle(int base) {
    // 코드를 완성하시오!
    if(base == 0) return; 
    printTriangle(base-1);
    for(int i=0 ; i<base ; i++) {
      System.out.print("*");
    } System.out.println();
  }
}

