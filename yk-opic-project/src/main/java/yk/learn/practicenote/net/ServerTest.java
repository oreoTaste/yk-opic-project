package yk.learn.practicenote.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
  final static int SOCKET_NUMBER = 9999;
  final static int BACK_LOG = 1;
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    System.out.println("Server On!");

    l1 :
      // when trying to change BACK_LOG of server socket.
      // instead of.. ServerSocket serversocket = new ServerSocket(SOCKET_NUMBER);
      // backlog can be added to one of params.
      try(ServerSocket serverSocket= new ServerSocket(SOCKET_NUMBER, BACK_LOG);
          ) {

        while(true) {
          Socket socket = serverSocket.accept();
          l2 :
            try(Scanner in = new Scanner(socket.getInputStream());
                PrintStream out = new PrintStream(socket.getOutputStream())) {

              String request = in.nextLine();
              if(request.equalsIgnoreCase("quit")) {
                break;
              }
              System.out.println(request);

              out.println(request);
              out.flush();

            } catch(Exception e) {
              System.out.println("Server System Error : " + e.getMessage());
              break l2;
            }

        }

        System.out.println("Server Off!");
        scanner.close();
      }
  }
}


