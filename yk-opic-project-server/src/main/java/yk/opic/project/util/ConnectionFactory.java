package yk.opic.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  String url;
  String username;
  String password;

  public ConnectionFactory(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;

  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, username, password);

  }
}
