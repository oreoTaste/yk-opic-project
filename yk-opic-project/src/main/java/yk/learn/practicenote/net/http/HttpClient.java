// HTTP 클라이언트 만들기
package yk.learn.practicenote.net.http;

import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

// HTTP 요청 프로토콜
// ---------------------------------
// GET [자원주소] HTTP/1.1 (CRLF)
// Host: [서버주소] (CRLF)
// (CRLF)
// ---------------------------------
//
// 프로토콜(protocol)?
// => 클라이언트/서버 간의 통신 규칙.
// => 데이터를 주고 받는 규칙.
//
public class HttpClient {
  final static int PORT_NUMBER = 9999;

  public static void main(String[] args) throws Exception {

    URL url = new URL("http://www.itworld.co.kr");
    System.out.println(url.toString());
    
    try (Socket socket = new Socket(url.getHost(), 80);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      out.print("GET /news/108939 HTTP/1.1\r\n");
      out.print("Host: www.itworld.co.kr\r\n");
      out.print("\r\n");

      while(true) {
        String str = in.nextLine();
        if(str == null) {
          break;
        }
        System.out.println(str);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}











