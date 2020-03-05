package yk.opic.project.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;
import yk.opic.project.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  LessonDao lessonDao;
  Prompt prompt;

  public PhotoBoardAddServlet(
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao, LessonDao lessonDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      PhotoBoard photoBoard = new PhotoBoard();

      photoBoard.setTitle(Prompt.inputString(in, out, "제목? "));
      photoBoard.setCreatedDate(new Date(System.currentTimeMillis()));
      photoBoard.setViewCount(0);

      Lesson lesson = lessonDao.findByNo(Prompt.inputInt(in, out, "수업번호? "));
      photoBoard.setLesson(lesson);

      int photoNo = photoBoardDao.insert(photoBoard);

      if(inputPhotoFile(in, out, photoNo) > 0) {
        out.println("사진을 저장했습니다.");
        out.flush();
      } else {
        out.println("수업번호가 유효하지 않습니다.");
        out.flush();
      }
    } catch(Exception e) {
      out.println("사진 저장 중 오류발생!");
      out.flush();
      e.printStackTrace();
    }
  }

  private int inputPhotoFile(Scanner in, PrintStream out, int photoNo) throws Exception {
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    int index = 0;
    for(int i = 0; ; ) {
      String filePath = Prompt.inputString(in, out, "사진파일?");

      if(filePath.length() > 0) {
        PhotoFile photoFile = new PhotoFile(filePath, photoNo);
        index = photoFileDao.insert(photoFile);
        i++;
        continue;

      } else if(filePath.length() == 0) {

        if(index == 0) {
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        } else {
          break;
        }
      }
    }
    return index;
  }

}
