package yk.opic.project.domain;

import java.sql.Date;
import java.util.List;

public class PhotoBoard {

  int no; // photo_id
  String title; // titl
  Date createdDate; // cdt
  int viewCount; // vw_cnt
  Lesson lesson; // lesson_id
  List<PhotoFile> files; //PhotoFile받음

  @Override
  public String toString() {
    return "PhotoBoard [no=" + no + ", title=" + title + ", createdDate=" + createdDate
        + ", viewCount=" + viewCount + ", lesson=" + lesson + ", files=" + files + "]";
  }
  public List<PhotoFile> getFiles() {
    return files;
  }
  public void setFiles(List<PhotoFile> files) {
    this.files = files;
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
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public Lesson getLesson() {
    return lesson;
  }
  public void setLesson(Lesson lesson) {
    this.lesson = lesson;
  }




}
