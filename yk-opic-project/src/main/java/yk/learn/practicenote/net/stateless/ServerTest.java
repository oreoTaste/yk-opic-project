// stateless
package yk.learn.practicenote.net.stateless;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
  static class RequestHandler extends Thread {
    ServerSocket serverSocket;

    RequestHandler(ServerSocket serverSocket) {
      this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
      commandServer(serverSocket);
      super.run();
    }
  }

  final static int SOCKET_NUMBER = 9999;
  final static int BACK_LOG = 1;
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    System.out.println("Server On!");

    // when trying to change BACK_LOG of server socket.
    // instead of.. ServerSocket serversocket = new ServerSocket(SOCKET_NUMBER);
    // backlog can be added to one of params.
    try(ServerSocket serverSocket= new ServerSocket(SOCKET_NUMBER, BACK_LOG)) {

      while(true) {

        RequestHandler requestHandler = new RequestHandler(serverSocket);
        requestHandler.start();

      }

    } catch(Exception e) {
      System.out.println("Server Off!");
      scanner.close();
    }

  }

  private static String commandServer(ServerSocket serverSocket) {

    try(Socket socket = serverSocket.accept();
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.println(request);

      out.println(request);
      out.flush();
      return request;

    } catch(Exception e) {
      System.out.println("Server System Error : " + e.getMessage());
      return null;
    }
  }
}


