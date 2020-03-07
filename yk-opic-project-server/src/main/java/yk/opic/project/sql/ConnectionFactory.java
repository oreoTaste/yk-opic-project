package yk.opic.project.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  String url;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  public ConnectionFactory(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;

  }

  public Connection getConnection() throws SQLException {
    Connection con = connectionLocal.get();

    if(con != null) {
      System.out.println(">> 스레드에 보관된 Connection 객체 리턴");
    }

    con = new ConnectionProxy(
        DriverManager.getConnection(url, username, password));
    System.out.println(">> 새로운 Connection 객체 생성");
    connectionLocal.set(con);

    return con;
  }


  public ConnectionProxy removeConnection() {
    ConnectionProxy con = new ConnectionProxy(connectionLocal.get());
    if(con != null) {
      connectionLocal.remove();
    }
    return con;
  }


}
