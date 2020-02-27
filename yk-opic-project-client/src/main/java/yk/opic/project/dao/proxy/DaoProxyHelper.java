package yk.opic.project.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DaoProxyHelper {
  String serverAddr;
  int portNumber;

  public DaoProxyHelper(String serverAddr, int portNumber) {
    this.serverAddr = serverAddr;
    this.portNumber = portNumber;
  }

  public Object execute(Worker worker) throws Exception {

    try(Socket socket = new Socket(serverAddr, portNumber);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      return worker.execute(out, in);


    }

  }
}
