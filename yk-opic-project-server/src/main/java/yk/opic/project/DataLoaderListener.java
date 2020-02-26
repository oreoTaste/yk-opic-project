package yk.opic.project;

import java.util.HashMap;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.dao.json.BoardJsonFileDao;
import yk.opic.project.dao.json.LessonJsonFileDao;
import yk.opic.project.dao.json.MemberJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  BoardJsonFileDao boardDao;
  LessonJsonFileDao lessonDao;
  MemberJsonFileDao memberDao;

  public DataLoaderListener() {
    boardDao = new BoardJsonFileDao("./board.json");
    lessonDao = new LessonJsonFileDao("./lesson.json");
    memberDao = new MemberJsonFileDao("./member.json");
  }

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    System.out.println("로딩시작");

    context.put("boardDao", boardDao);
    context.put("lessonDao", lessonDao);
    context.put("memberDao", memberDao);
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");
  }






}
