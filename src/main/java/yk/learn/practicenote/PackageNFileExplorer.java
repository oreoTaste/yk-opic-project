// 활용 - 패키지명을 같이 출력하기 (Fully
package yk.learn.practicenote;

import java.io.File;
import java.io.IOException;

public class PackageNFileExplorer {

  public static void main(String[] args) throws Exception {

    File dir = new File(".");
    System.out.println(dir.getCanonicalPath());
    printList(dir, "");

  }

  static void printList(File dir, String packageName) throws IOException {

    File[] files = dir.listFiles( pathname -> {
      if(pathname.isHidden()) {
        return false;
      }
      if(pathname.getName().contains("$")) {
        return false;
      }
      if(pathname.isDirectory() ||
          pathname.isFile() && pathname.getName().endsWith(".class")) {
        return true;
      } else {
        return false;
      }});

    for(File file : files) {

      if(file.isDirectory()) {
        printList(file, packageName + file.getName() + ".");
      } else {
        System.out.println(packageName + file.getName().replace(".class", ""));
      }
    }
  }
}






