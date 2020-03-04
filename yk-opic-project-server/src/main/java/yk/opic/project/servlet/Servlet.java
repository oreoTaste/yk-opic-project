package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;

public interface Servlet {
  void service(Scanner in, PrintStream out) throws Exception;
}
