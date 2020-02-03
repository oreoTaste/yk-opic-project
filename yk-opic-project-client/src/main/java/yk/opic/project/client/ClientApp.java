package yk.opic.project.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientApp {
  static Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) throws UnknownHostException, IOException {

    try(
        Socket socket = getSocket();
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream());
        ) {

      System.out.println("서버 접속완료");
      System.out.println("서버 메시지 : " + in.nextLine());
      System.out.print("전송할 메시지? ");
      String msg = scanner.nextLine();
      out.println(msg);
    } catch(UnknownHostException e) {
      System.out.println("socket 생성중 오류");
    } catch(Exception e) {
      System.out.println("오류발생");
    }



    scanner.close();
  }


  private static Socket getSocket() throws UnknownHostException, IOException {
    System.out.print("접속 서버 주소 : ");
    String serverAddr = scanner.nextLine();
    System.out.print("접속 포트 번호 : ");
    int port = Integer.parseInt(scanner.nextLine());
    return new Socket(serverAddr, port);
  }

}
