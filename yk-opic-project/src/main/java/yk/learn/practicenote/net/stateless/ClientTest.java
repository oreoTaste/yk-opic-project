// stateless
package yk.learn.practicenote.net.stateless;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {

  final static String INNET_ADDRESS = "localhost";
  final static int SOCKET_NUMBER = 9999;
  final static int TIME_OUT = 5000;
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    System.out.println("Client On!");

    System.out.print(" >");
    String msg = scanner.nextLine();
    // when trying to set timeout of client
    /*
    Socket socket = new Socket("localhost",9999);
    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());
     */
    Socket socket = new Socket();
    socket.connect(new InetSocketAddress(INNET_ADDRESS, SOCKET_NUMBER), TIME_OUT);

    l1:
      try(PrintStream out = new PrintStream(socket.getOutputStream());
          Scanner in = new Scanner(socket.getInputStream())) {

        out.println(msg);
        out.flush();

        System.out.println("response : " + in.nextLine());

      }

    socket.close();
    System.out.println("Client Off!");
  }
}


