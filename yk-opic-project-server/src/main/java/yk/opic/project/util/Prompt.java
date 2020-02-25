package yk.opic.project.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);
  
  public static int inputInt(String title) {
    System.out.println(title);
    return Integer.parseInt(scanner.nextLine());
  }
  
  public static String inputString(String title) {
    System.out.println(title);
    return scanner.nextLine();
  }
  
  public static Date inputDate(String title) {
    System.out.println(title);
    return Date.valueOf(scanner.nextLine());
  }
  
}
