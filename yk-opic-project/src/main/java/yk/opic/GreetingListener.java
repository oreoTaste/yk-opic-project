package yk.opic;

import java.util.HashMap;
import yk.opic.context.ApplicationContextListener;

public class GreetingListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(HashMap<String, Object> map) {
    System.out.println("[수업관리시스템]에 오신 것을 환영합니다!");
  }

  @Override
  public void contextDestroyed(HashMap<String, Object> map) {
    System.out.println("다음에 또 방문해주세요 :)");
  }

}
