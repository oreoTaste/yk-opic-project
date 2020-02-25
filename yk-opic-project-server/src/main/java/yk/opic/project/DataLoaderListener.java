package yk.opic.project;

import java.util.HashMap;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.dao.BoardObjectFileDao;
import yk.opic.project.dao.LessonObjectFileDao;
import yk.opic.project.dao.MemberObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  BoardObjectFileDao boardDao;
  LessonObjectFileDao lessonDao;
  MemberObjectFileDao memberDao;
  
  public DataLoaderListener() {
    boardDao = new BoardObjectFileDao("./board.ser");
    lessonDao = new LessonObjectFileDao("./lesson.ser");
    memberDao = new MemberObjectFileDao("./member.ser");
  }

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    System.out.println("로딩시작");

    context.put("boardDao", lessonDao);
    context.put("lessonDao", boardDao);
    context.put("memberDao", memberDao);
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");
  }






}
