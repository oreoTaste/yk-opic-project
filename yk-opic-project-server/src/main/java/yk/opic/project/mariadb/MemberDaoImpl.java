package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;

public class MemberDaoImpl implements MemberDao {

  @Override
  public int insert(Member member) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      return stmt.executeUpdate("INSERT INTO lms_member (name, email, pwd, cdt, tel, photo)"
          + " values('" + member.getName() + "',"
          + " '" + member.getEmail() + "',"
          + " '" + member.getPassword() + "',"
          + " '" + member.getRegisteredDate() + "',"
          + " '" + member.getTel() + "',"
          + " '" + member.getPhoto() + "');");
    }
  }

  @Override
  public List<Member> findAll() throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "111");
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_member");
      List<Member> list = new ArrayList<>();
      while(rs.next()) {
        Member member = new Member();
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        list.add(member);
      }
      return list;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_member where member_id = " + no);

      if(rs.next()) {
        Member member = new Member();
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        return member;
      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Member member) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "UPDATE lms_member SET"
              + " name = " + member.getName()
              + " email = " + member.getEmail()
              + " pwd = " + member.getPassword()
              + " cdt = " + member.getRegisteredDate()
              + " tel = " + member.getTel()
              + " photo = " + member.getPhoto()
              + " WHERE member_id = " + member.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "DELETE from lms_member WHERE member_id = " + no);
    }
  }





}
