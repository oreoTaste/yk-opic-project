package yk.opic.project.domain;

public class PhotoFile {

  int no; // PhotoFile의 no
  int photoNo; // PhotoBoard의 no
  String filePath;

  public PhotoFile() {

  }

  public PhotoFile(String filePath, int photoNo) {
    this.filePath = filePath;
    this.photoNo = photoNo;
  }

  public PhotoFile(int no, String filePath, int photoNo) {
    this(filePath, photoNo);
    this.no = no;
  }

  @Override
  public String toString() {
    return "PhotoFile [no=" + no + ", photoNo=" + photoNo + ", filePath=" + filePath + "]";
  }

  public int getNo() {
    return no;
  }

  public PhotoFile setNo(int no) {
    this.no = no;
    return this;
  }

  public int getPhotoNo() {
    return photoNo;
  }

  public PhotoFile setPhotoNo(int photoNo) {
    this.photoNo = photoNo;
    return this;
  }

  public String getFilePath() {
    return filePath;
  }

  public PhotoFile setFilePath(String filePath) {
    this.filePath = filePath;
    return this;
  }




}
