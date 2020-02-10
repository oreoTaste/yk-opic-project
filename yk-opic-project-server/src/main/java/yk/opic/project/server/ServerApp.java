package yk.opic.project.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerApp {
  public static void main(String[] args) throws UnknownHostException, IOException {
    Scanner scanner = new Scanner(System.in);

    ServerSocket serverSocket = new ServerSocket(9999);
    Socket socket = serverSocket.accept();
    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

    System.out.println("서버가동");
    while(true) {
      System.out.print(" >");
      try {
        System.out.println("클라이언트 측 : " + in.readUTF());
        String msg = scanner.nextLine();
        out.writeUTF(msg);
      } catch(Exception e) {
        System.out.println(e.getMessage());
      }
    }
    /*
    try(ServerSocket serverSocket = new ServerSocket(9999)){
      System.out.println("서버 연결 완료");

      while(true) {
        System.out.println("...클라이언트 접속 대기");

        processRequest(serverSocket);

      }
    } catch (Exception e) {
      System.out.println("서버 준비중 오류발생");
    }
     */
  }

  private static void processRequest(ServerSocket serverSocket) {

    try(Socket socket = serverSocket.accept();
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())
        ) {
      System.out.println("클라이언트 연결완료");
      out.println("Hello(손영국) - 192.168.1.39, 9999");

      System.out.println("클라이언트 메시지 :" + in.nextLine());
      System.out.println("=======================================");
    } catch (Exception e) {
      System.out.println("클라이언트 접속 이후 오류발생");
    }

  }
}
