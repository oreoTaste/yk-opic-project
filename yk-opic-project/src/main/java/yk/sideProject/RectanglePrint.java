package yk.sideProject;

// 1. class 분리 이해하기
// 2. deprecated annotation 이해하기
public class RectanglePrint {
  public static void main(String[] args) {
    int base = Console.inputInt();
    int line = 0;
    while (line++ < base) {
      Graphic.draw(line);
      System.out.println();
    }
  }
  

  
}
/*
*
**
***
****
*****
*/