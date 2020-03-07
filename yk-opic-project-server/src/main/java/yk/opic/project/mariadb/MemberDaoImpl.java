package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.project.util.ConnectionFactory;

public class MemberDaoImpl implements MemberDao {
  ConnectionFactory conFactory;

  public MemberDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }


  @Override
  public int insert(Member member) throws Exception {

    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

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

    try(Connection con = conFactory.getConnection();
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

    try(Connection con = conFactory.getConnection();
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

    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(
          "UPDATE lms_member SET"
              + " name = '" + member.getName()
              + "', email = '" + member.getEmail()
              + "', pwd = '" + member.getPassword()
              + "', cdt = '" + member.getRegisteredDate()
              + "', tel = '" + member.getTel()
              + "', photo = '" + member.getPhoto()
              + "' WHERE member_id = " + member.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(
          "DELETE from lms_member WHERE member_id = " + no);
    }
  }

  @Override
  public List<Member> findByKeyword(String word) throws Exception {
    String wordWithPercent = "%" + word + "%";
    try(Connection con = conFactory.getConnection();
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









