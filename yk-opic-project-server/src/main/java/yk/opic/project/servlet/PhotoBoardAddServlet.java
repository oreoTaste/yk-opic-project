package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;
import yk.opic.project.service.LessonService;
import yk.opic.project.service.PhotoBoardService;
import yk.opic.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {
  PhotoBoardService PhotoBoardService;
  LessonService lessonService;

  public PhotoBoardAddServlet(PhotoBoardService PhotoBoardService, LessonService lessonService) {
    this.PhotoBoardService = PhotoBoardService;
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();

    photoBoard.setTitle(Prompt.inputString(in, out, "제목? "));

    int lessonNo = Prompt.inputInt(in, out, "수업번호? ");
    Lesson lesson = lessonService.get(lessonNo);
    if(lesson == null) {
      out.println("수업번호가 유효하지 않습니다.");
      return;
    }
    photoBoard.setLesson(lesson);


    List<PhotoFile> photoFiles = inputPhotoFile(in, out);
    for(PhotoFile photoFile : photoFiles) {
      System.out.println(photoBoard.getNo());/////
      photoFile.setPhotoNo(photoBoard.getNo());
    }
    photoBoard.setFiles(photoFiles);

    if(PhotoBoardService.add(photoBoard) > 0) {
      out.println("사진을 저장했습니다.");
      out.flush();
    } else {
      out.println("사진 저장에 실패했습니다.");
    }
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
