package yk.sideProject;

import java.util.Scanner;

public class Graphic {
  static void draw(int line, String str) {
    int count = 0;
    while (count++ < line) {
      System.out.print(str);
    }
  }

  static void draw(int line) {
    int count = 0;
    while (count++ < line) {
      System.out.print("*");
    }
  }

}
