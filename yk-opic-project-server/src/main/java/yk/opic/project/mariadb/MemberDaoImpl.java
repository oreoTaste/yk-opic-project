package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.project.sql.DataSource;

public class MemberDaoImpl implements MemberDao {
  DataSource dataSource;

  public MemberDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT * FROM lms_member"
                + " WHERE email = ? and pwd = password(?)")) {

      stmt.setString(1, email);
      stmt.setString(2, password);

      ResultSet rs = stmt.executeQuery();

      Member member = new Member();
      if(rs.next()) {
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
      }
      return member;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  @Override
  public int insert(Member member) throws Exception {

    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_member (name, email, pwd, cdt, tel, photo)"
                + " values(?,?, password(?),?,?,?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setDate(4,member.getRegisteredDate());
      stmt.setString(5, member.getTel());
      stmt.setString(6, member.getPhoto());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findAll() throws Exception {

    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_member");
      List<Member> list = new ArrayList<>();
      while(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
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

    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_member where member_id = " + no);

      if(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
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

    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE lms_member SET"
                + " name = ?, email = ?, pwd = password(?), cdt = ?, tel = ?, photo = ?"
                + " WHERE member_id = ?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setDate(4, member.getRegisteredDate());
      stmt.setString(5, member.getTel());
      stmt.setString(6, member.getPhoto());
      stmt.setInt(7, member.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "DELETE from lms_member WHERE member_id = ?")) {

      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findByKeyword(String word) throws Exception {
    String wordWithPercent = "%" + word + "%";
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery(
          "SELECT * FROM lms_member"
              + " WHERE name like '" + wordWithPercent
              + "' or email like '" + wordWithPercent
              + "' or tel like '" + wordWithPercent
              + "' or photo like '" + wordWithPercent
              + "'");
      List<Member> list = new ArrayList<>();
      while(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
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





}









