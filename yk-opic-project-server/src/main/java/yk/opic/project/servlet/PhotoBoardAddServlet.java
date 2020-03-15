package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.dao.LessonDao;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;
import yk.opic.sql.TransactionTemplate;
import yk.opic.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  LessonDao lessonDao;
  TransactionTemplate txTemplate;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      LessonDao lessonDao, TransactionTemplate txTemplate) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.lessonDao = lessonDao;
    this.txTemplate = txTemplate;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();

    photoBoard.setTitle(Prompt.inputString(in, out, "제목? "));

    int lessonNo = Prompt.inputInt(in, out, "수업번호? ");
    System.out.println("lesson 찾기전");
    Lesson lesson = lessonDao.findByNo(lessonNo);
    System.out.println("lesson 찾은후");
    if(lesson == null) {
      out.println("수업번호가 유효하지 않습니다.");
      return;
    }
    System.out.println("lesson setting전");
    photoBoard.setLesson(lesson);
    System.out.println("lesson setting후");


    txTemplate.execute(() -> {
      if(photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시글 등록에 실패했습니다.");
      }

      System.out.println("photoFile각각넣기전");
      List<PhotoFile> photoFiles = inputPhotoFile(in, out);
      System.out.println("photoFile각각넣은후");
      for(PhotoFile photoFile : photoFiles) {
        System.out.println(photoBoard.getNo());/////
        photoFile.setPhotoNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
      }
      out.println("사진을 저장했습니다.");
      out.flush();
      return null;
    });
  }

  private List<PhotoFile> inputPhotoFile(Scanner in, PrintStream out) throws Exception {
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    List<PhotoFile> photoFiles = new ArrayList<>();

    int index = 0;
    while(true) {
      String filePath = Prompt.inputString(in, out, "사진파일?");

      if(filePath.length() > 0) {
        PhotoFile photoFile = new PhotoFile().setFilePath(filePath);
        photoFiles.add(photoFile);
        index++;
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
    return photoFiles;
  }

}
