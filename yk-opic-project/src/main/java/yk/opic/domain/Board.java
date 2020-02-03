package yk.opic.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String title;
  private Date date;
  private int viewCount;


  public static Board valueOf(String line) {
    String[] data = line.split(",");

    Board board = new Board();
    board.setNo(Integer.parseInt(data[0]));
    board.setTitle(data[0]);
    board.setDate(Date.valueOf(data[0]));
    board.setViewCount(Integer.valueOf(data[0]));
    return board;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%d\n",
        getNo(), getTitle(), getDate(), getViewCount());
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public boolean equals(Board value) {
    if(value.getClass() != Board.class) {
      return false;
    }

    Board other = value;

    if(this.no != other.no) {
      return false;
    }

    if(!this.title.equals(other.title)) {
      return false;
    }

    //if(this.date != other.date) {
    //  return false;
    //}

    if(this.viewCount != other.viewCount) {
      return false;
    }
    return true;
  }
}
