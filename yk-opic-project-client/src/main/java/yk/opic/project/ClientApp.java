package yk.opic.project;

import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import yk.opic.project.util.Prompt;

public class ClientApp {
  static Scanner scanner = new Scanner(System.in);
  static Prompt prompt = new Prompt(scanner);

  Queue<String> commandQueue;
  Deque<String> commandStack;

  public ClientApp() throws ClassNotFoundException, SQLException {
    commandQueue = new LinkedList<>();
    commandStack = new ArrayDeque<>();
  }

  public static void main(String[] args) throws Exception {

    System.out.println("클라이언트 시작");

    ClientApp clientApp = new ClientApp();
    clientApp.service();

  }

  public void service() {

    try {
      while(true) {
        String command = prompt.inputString("\n명령> ");

        if (command.length() == 0) {
          continue;
        }
        if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("/server/stop")) {
          System.out.println("...안녕");
          break;
        } else if (command.equals("history")) {
          printCommandHistory(commandQueue.iterator());
          continue;
        } else if (command.equals("history2")) {
          printCommandHistory(commandStack.iterator());
          continue;
        }

        commandStack.push(command);
        commandQueue.offer(command);
        System.out.println("<<");
        processCommand(command);
        System.out.println(">>");

      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    scanner.close();
  }

  private void processCommand(String command) throws Exception {
    String host = "localhost";
    int port = 9999;
    String servletPath;
    final String STARTER = "yk://";

    if(!command.startsWith(STARTER)) {
      System.out.println("명령어 형식이 옳지 않습니다.");
      return;
    }

    try {
      String url = command.substring(STARTER.length());

      host = getHost(url);
      port = getPort(url);

      servletPath = getServletPath(url);
    } catch(Exception e) {
      System.out.println("명령어 형식이 옳지 않습니다.");
      return;
    }

    try(Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())){

      out.println(servletPath);
      out.flush();

      while(true) {
        if(!in.hasNext())
          break;
        String response = in.nextLine();
        if(response.equalsIgnoreCase("!end!")) {
          break;
        } else if(response.equalsIgnoreCase("!{}!")) {
          String input = prompt.inputString("");
          out.println(input);
          out.flush();
        } else {
          System.out.println(response);
        }
      }

    }
  }
  private String getServletPath(String url) {
    return url.substring(url.indexOf("/"));
  }

  private String getHost(String url) {

    String[] str = url.substring(0, url.indexOf("/")).split(":");
    return str[0];
  }

  private int getPort(String url) {

    if(url.indexOf(":") == -1)
      return 9999;
    else
      return Integer.parseInt(url.substring(url.indexOf(":") + 1, url.indexOf("/")));
  }


  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if (count % 5 == 0) {
        System.out.print(":(중지하고 싶으면 q)");
        String str = scanner.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

}
