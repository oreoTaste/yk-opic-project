package yk.opic.project.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;
import yk.opic.project.service.PhotoBoardService;
import yk.opic.util.Component;
import yk.opic.util.Prompt;

@Component("/photoboard/detail")
public class PhotoBoardDetailServlet implements Servlet {
  PhotoBoardService PhotoBoardService;

  public PhotoBoardDetailServlet(PhotoBoardService PhotoBoardService) {
    this.PhotoBoardService = PhotoBoardService;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int photoNo = Prompt.inputInt(in, out, "번호? ");
      PhotoBoard photoBoard = PhotoBoardService.get(photoNo);

      if(photoBoard != null) {
        out.printf("제목 : %s\n", photoBoard.getTitle());
        out.printf("작성일 : %1$tF %1$tH:%1$tM:%1$tS\n", photoBoard.getCreatedDate());
        out.printf("조회수 : %d\n", photoBoard.getViewCount());
        out.printf("수업명 : %s\n", photoBoard.getLesson().getTitle());

        out.println("사진 파일:");

        for(PhotoFile f : photoBoard.getFiles()) {
          out.println("> " + f.getFilePath());
        }

      } else {
        out.println("해당 번호의 사진 게시글이 없습니다.");
      }
      out.flush();
    } catch(Exception e) {
      out.println("사진 게시물 조회중 오류발생!");
      out.flush();
      e.printStackTrace();
    }

  }

}
