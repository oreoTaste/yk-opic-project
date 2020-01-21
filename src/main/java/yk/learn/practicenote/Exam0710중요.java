// 생성자 레퍼런스
package yk.learn.practicenote;

import java.util.ArrayList;
import java.util.List;

public class Exam0710중요 {
  
  static interface ListFactory {
    List create();
  }

  public static void main(String[] args) {
    
    // 인터페이스에 정의된 메서드가
    // 생성자의 형식과 일치한다면
    // 메서드 레퍼런스로 생성자를 지정할 수 있다.
    
    ListFactory f1 = ArrayList::new;
    List list = f1.create();
    
    System.out.println(list instanceof ArrayList);
    System.out.println(list.getClass().getName());
  }
}


