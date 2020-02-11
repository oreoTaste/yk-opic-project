// 타입아웃 시간 설정하기
package yk.learn.practicenote.net.stateful;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {
  
  static class RequestHandler extends Thread {
    Socket socket;
    
    RequestHandler(Socket socket) {
      this.socket = socket;
    }
    
    @Override
    public void run() {
      
    }
    
  }
  
  
  final static String INNET_ADDRESS = "localhost";
  final static int SOCKET_NUMBER = 9999;
  final static int TIME_OUT = 5000;
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    System.out.println("Client On!");

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

        System.out.print(" >");
        out.println(scanner.nextLine());
        out.flush();

        System.out.println("response : " + in.nextLine());

      }

    System.out.println("Client Off!");
  }
}


