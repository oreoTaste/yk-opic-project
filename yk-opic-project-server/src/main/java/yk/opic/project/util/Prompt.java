package yk.opic.project.util;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  public static int inputInt(Scanner in, PrintStream out, String title) {
    out.println(title);
    out.println("!{}!");
    return Integer.parseInt(in.nextLine());
  }

  public static String inputString(Scanner in, PrintStream out, String title) {
    out.println(title);
    out.println("!{}!");
    return in.nextLine();
  }

  public static Date inputDate(Scanner in, PrintStream out, String title) {
    out.println(title);
    out.println("!{}!");
    return Date.valueOf(in.nextLine());
  }

  public static String inputString(Scanner in, PrintStream out, String title, String defaultValue) {
    out.println(title);
    out.println("!{}!");
    if(title.length() == 0) {
      return defaultValue;
    }
    return in.nextLine();
  }

  public static Date inputDate(Scanner in, PrintStream out, String title, Date defaultValue) {
    out.println(title);
    out.println("!{}!");
    if(title.length() == 0) {
      return defaultValue;
    }
    return Date.valueOf(in.nextLine());
  }

  public static int inputInt(Scanner in, PrintStream out, String title, int defaultValue) {
    out.println(title);
    out.println("!{}!");
    if(title.length() == 0) {
      return defaultValue;
    }
    return Integer.parseInt(in.nextLine());
  }

}
