package yk.sideProject;

//1. class 분리 이해하기
//2. deprecated annotation 이해하기
public class RegularTrianglePrint {
  public static void main(String[] args) {
    final int base = Console.inputInt();
    final int top = base >> 1;
    int blankLoop = top;
    int wildcardLoop = 1;

    for (int i = 0 ; i < top+1 ; i++) {
      Graphic.draw(blankLoop, " ");
      blankLoop--;
      
      Graphic.draw(wildcardLoop);
      System.out.println();
      wildcardLoop += 2;
    }
  }

}
/*

  *         2 1
 ***        1 3
*****       0 5

*/