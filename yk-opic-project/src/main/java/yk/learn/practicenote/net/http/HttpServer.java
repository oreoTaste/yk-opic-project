// HTTP 서버 만들기
package yk.learn.practicenote.net.http;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// HTTP 응답 프로토콜
//--------------------------------
// HTTP/1.1 200 OK(CRLF)
// Content-Type: text/html; charset=UTF-8(CRLF)
// (CRLF)
// 보낼 데이터
//--------------------------------
public class HttpServer {
  final static int PORT_NUMBER = 9999;

  public static void main(String[] args) throws Exception {

    System.out.println("Server On!");

    ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

    while(true) {

      try(Socket socket = serverSocket.accept();
          Scanner in = new Scanner(socket.getInputStream());
          PrintStream out = new PrintStream(socket.getOutputStream())){

        while(true) {
          String str = in.nextLine();
          System.out.println(str);
          if(str == null) {
            break;
          }
        }
        
        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Content-Type: text/html;charset=UTF-8\r\n");
        out.print("\r\n");
        out.print("<html> <body> <h1> Hello! It's context section. </h1> </body> </html>");

      } catch(Exception e) {
        System.out.println("Server System Error : " + e.getMessage());
      }
      System.out.println("Server Off!");
      break;
    }

  }

}







