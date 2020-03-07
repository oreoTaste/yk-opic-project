package yk.opic.project.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import yk.opic.project.DataLoaderListener;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.project.domain.PhotoFile;
import yk.opic.project.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    DataLoaderListener.con.setAutoCommit(false);

    try {
      PhotoBoard oldPhoto = photoBoardDao.findByNo(Prompt.inputInt(in, out, "번호? "));

      if(oldPhoto == null) {
        out.println("해당 번호의 사진 게시글이 없습니다.");
        return;
      }

      PhotoBoard newPhoto = new PhotoBoard();

      newPhoto.setTitle(
          Prompt.inputString(in, out,
              String.format("제목? (%s)", oldPhoto.getTitle()),
              oldPhoto.getTitle()));
      newPhoto.setCreatedDate(new Date(System.currentTimeMillis()));
      newPhoto.setViewCount(0);

      /*
      Lesson lesson = new Lesson();
      lesson.setNo(Prompt.inputInt(in, out, "수업번호? "));
      newBoard.setLesson(lesson);
       */

      if (newPhoto.equals(oldPhoto)) {
        out.println("해당 번호의 사진 게시글이 없습니다.");
        out.flush();
      } else {
        int index = photoBoardDao.update(newPhoto);

        if(index > 0) {


          out.println("사진파일:");
          List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(oldPhoto.getNo());
          for(PhotoFile f : oldPhotoFiles) {
            out.printf("> %s\n",f.getFilePath());
          }

          out.println();
          out.println("사진은 일부만 변경할 수 없습니다.");
          out.println("전체를 새로 등록해야 합니다.");

          String response = Prompt.inputString(in, out, //
              "사진을 변경하시겠습니까?(y/N) ");

          ArrayList<PhotoFile> photoFiles = null;
          if(response.equalsIgnoreCase("y")) {

            photoFileDao.deleteAll(oldPhoto.getNo());
            out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
            out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

            photoFiles = new ArrayList<>();
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
          }

          for(PhotoFile photoFile : photoFiles) {
            photoFile.setPhotoNo(oldPhoto.getNo());
            photoFileDao.insert(photoFile);
          }

          DataLoaderListener.con.commit();
          DataLoaderListener.con.setAutoCommit(true);
          out.println("사진을 변경했습니다.");
        } else {
          DataLoaderListener.con.rollback();
          DataLoaderListener.con.setAutoCommit(true);
          out.println("해당 번호의 사진 게시글이 없습니다.");
        }
        out.flush();
      }

    } catch(Exception e) {
      DataLoaderListener.con.rollback();
      DataLoaderListener.con.setAutoCommit(true);
      out.println("사진 게시글 변경 중 오류발생!");
      out.flush();
      e.printStackTrace();
    }


  }

}
