// connectionless 클라이언트 - 연결없이 데이터 수신
package yk.learn.practicenote.net.udp_connectionless;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;
// 비연결(connectionless)
// => 연결없이 데이터를 송수신한다.
// => 상대편이 네트워크에 연결되어 있지 않다면, 그 데이터는 버려진다.
// => 그래서 전송 여부를 신뢰할 수 없다.
// => 실생활에서 "편지"와 같다.
// => 예) ping
//

public class UdpServer {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    byte[] buf = new byte[8196];

    DatagramSocket socket = new DatagramSocket(9999);
    DatagramPacket emptyPacket = new DatagramPacket(buf, buf.length);

    socket.receive(emptyPacket);

    for(int i = 0; i < emptyPacket.getLength(); i++) {
      System.out.printf("%c", buf[i]);
    } System.out.println();

    socket.close();
    scanner.close();

    String msg = new String(emptyPacket.getData(), 0, emptyPacket.getLength(), "UTF-8");
    System.out.println(msg);

  }
}





