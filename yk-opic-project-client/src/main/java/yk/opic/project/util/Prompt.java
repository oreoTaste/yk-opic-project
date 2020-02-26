package yk.opic.project.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  Scanner input;

  public Prompt(Scanner input) {
    this.input = input;
  }

  public int inputInt(String msg) {
    System.out.print(msg);
    return Integer.valueOf(input.nextLine());
  }

  public String inputString(String msg) {
    System.out.print(msg);
    return input.nextLine();
  }

  public Date inputDate(String msg) {
    System.out.print(msg);
    return Date.valueOf(input.nextLine());
  }

  public int inputInt(String msg, int value) {
    System.out.print(msg);
    String temp = input.nextLine();
    if(temp.length() == 0) {
      return value;
    }
    return Integer.parseInt(input.nextLine());
  }

  public String inputString(String msg, String value) {
    System.out.print(msg);
    String temp = input.nextLine();
    if(temp.length() == 0) {
      return value;
    }
    return temp;
  }

  public Date inputDate(String msg, Date value) {
    System.out.print(msg);
    String temp = input.nextLine();
    if(temp.length() == 0) {
      return value;
    }
    return Date.valueOf(temp);
  }
}
