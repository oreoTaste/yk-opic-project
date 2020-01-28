// 활용 - 지정한 폴더 및 그 하위 폴더까지 모두 검색하여 내용을 출력하라
package yk.learn.practicenote;

import java.io.File;

public class FileDiagram {

  public static void main(String[] args) throws Exception {

    // 결과 예시)
    // src/
    //   main/
    //     java/
    //       com/
    //         Hello.java
    //         Hello2.java
    // build.gradle
    // settings.gradle
    // Hello.java

    File dir = new File(".");
    System.out.println(dir.getCanonicalPath());
    printList(dir, 1);
  }

  static void printList(File dir, int lvl) {
    
    File[] files = dir.listFiles();
    for(File file : files) {
      
      printIndent(lvl);

      if(file.isDirectory() && !file.isHidden()) {
        System.out.printf("%s/\n",file.getName());
        printList(file, lvl+1);
      }
      else {
        System.out.print("ㄴㅡ ");
        System.out.printf("%s\n", file.getName());
      }
    }
  }
  
  static void printIndent (int level) {
    for(int i = 0 ; i < level; i++) {
      System.out.print("  ");
    }
  }

}







