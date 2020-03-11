package yk.opic.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
  String url;
  String username;
  String password;

  ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
  List<Connection> conList = new ArrayList<>();

  public DataSource(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {

    Connection con = threadLocal.get();
    if(con != null) {
      System.out.println("thread에 보관중인 connection 객체 리턴");
      return con;
    }

    if(conList.size() > 0) {
      System.out.println("기존에 반납받은 Connection 객체 재사용");
      con = conList.remove(0);
    } else {
      System.out.println("새로운 Connection 객체 생성");
      con = new ConnectionProxy(
          DriverManager.getConnection(url, username, password));
    }
    threadLocal.set(con);
    System.out.println("현재 보관중인 객체 : " + conList.size() + "개");
    return con;
  }


  public Connection removeConnection() {
    Connection con = threadLocal.get();
    if(con != null) {
      threadLocal.remove();
      conList.add(con);
    }
    System.out.println("현재 보관중인 객체 : " + conList.size() + "개");
    return con;
  }

  public void clean() {
    while(conList.size() > 0) {
      try {
        ((ConnectionProxy)conList.remove(0)).realClose();
      } catch (SQLException e) {
      }
    }
  }

}
