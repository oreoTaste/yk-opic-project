package yk.opic.project.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.domain.Lesson;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;
import yk.opic.project.service.PhotoBoardService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/photoboard/update")
public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardService photoBoardService;

  public PhotoBoardUpdateServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int index = Prompt.inputInt(in, out, "번호? ");
    PhotoBoard oldPhoto = photoBoardService.get(index);

    if(oldPhoto == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }

    PhotoBoard newPhoto = new PhotoBoard();

    newPhoto.setNo(oldPhoto.getNo());
    newPhoto.setTitle(
        Prompt.inputString(in, out,
            String.format("제목? (%s)", oldPhoto.getTitle()),
            oldPhoto.getTitle()));
    newPhoto.setCreatedDate(new Date(System.currentTimeMillis()));
    newPhoto.setViewCount(0);

    Lesson lesson = new Lesson();
    lesson.setNo(oldPhoto.getLesson().getNo());
    newPhoto.setLesson(lesson);

    printFiles(in, out, oldPhoto);

    out.println();
    out.println("사진은 일부만 변경할 수 없습니다.");
    out.println("전체를 새로 등록해야 합니다.");

    String response = Prompt.inputString(in, out, //
        "사진을 변경하시겠습니까?(y/N) ");

    if(response.equalsIgnoreCase("y")) {
      newPhoto.setFiles(inputPhotoFiles(in, out));
    }
    photoBoardService.update(newPhoto);
    out.println("사진을 변경했습니다.");

  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    List<PhotoFile> photoFiles = new ArrayList<>();
    while(true) {
      String filePath = Prompt.inputString(in, out, "사진파일? ");

      if(filePath.length() == 0) {
        if(photoFiles.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        }
      }
      photoFiles.add(new PhotoFile().setFilePath(filePath));
    }
    return photoFiles;
  }

  private void printFiles(Scanner in, PrintStream out, PhotoBoard oldPhoto) {
    out.println("사진파일:");
    List<PhotoFile> oldPhotoFiles = oldPhoto.getFiles();
    for(PhotoFile f : oldPhotoFiles) {
      out.printf("> %s\n",f.getFilePath());
    }
  }
}