// connectionless
package yk.learn.practicenote.net.udp_connectionless;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
  final static String HOST_NAME = "localhost";
  final static int PORT_NUMBER = 9999;

  public static void main(String[] args) throws Exception {

    byte[] bytes = "Hello".getBytes("UTF-8");

    DatagramSocket socket = new DatagramSocket();

    DatagramPacket packet = new DatagramPacket(
        bytes,
        bytes.length,
        InetAddress.getByName(HOST_NAME),
        PORT_NUMBER);

    socket.send(packet);
    socket.close();

  }
}





