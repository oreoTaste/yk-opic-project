package yk.opic.project;

import java.util.HashMap;
import yk.opic.project.context.ApplicationContextListener;
import yk.opic.project.dao.json.BoardJsonFileDao;
import yk.opic.project.dao.json.LessonJsonFileDao;
import yk.opic.project.dao.json.MemberJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(HashMap<String, Object> context) {
    System.out.println("로딩시작");

    context.put("boardDao", new BoardJsonFileDao("./board.json"));
    context.put("lessonDao", new LessonJsonFileDao("./lesson.json"));
    context.put("memberDao", new MemberJsonFileDao("./member.json"));
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> context) {
    System.out.println("...안녕!");
  }






}
