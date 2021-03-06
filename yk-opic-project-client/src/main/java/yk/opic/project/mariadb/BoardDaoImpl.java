package yk.opic.project.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yk.opic.project.dao.BoardDao;
import yk.opic.project.domain.Board;

public class BoardDaoImpl implements BoardDao {

  Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Board board) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      return stmt.executeUpdate("INSERT INTO lms_board (conts)"
          + " values('"+ board.getTitle() + "')");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {

    try(Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_board");
      List<Board> list = new ArrayList<>();
      while(rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        list.add(board);
      }
      return list;
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      ResultSet rs = stmt.executeQuery("SELECT * FROM lms_board where board_id = " + no);

      if(rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        return board;
      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Board board) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "UPDATE lms_board SET"
              + " conts = " + board.getTitle()
              + " cdt = now()"
              + " vw_cnt = 0"
              + " WHERE board_id = " + board.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);
      return stmt.executeUpdate(
          "DELETE from lms_board WHERE board_id = " + no);
    }
  }





}
