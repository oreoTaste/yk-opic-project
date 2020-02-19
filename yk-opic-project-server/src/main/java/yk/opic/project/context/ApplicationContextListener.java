package yk.opic.project.context;

import java.util.HashMap;

public interface ApplicationContextListener {
  void contextInitialized(HashMap<String, Object> map);

  void contextDestroyed(HashMap<String, Object> map);
}
