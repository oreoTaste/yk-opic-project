package yk.learn.practicenote;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class FileIOTest {
  public static void main(String[] args) throws Exception {

    File file = new File("src/main/java/yk/learn/practicenote/test.txt");
    File dir = new File(file.getParent());

    if(dir.mkdirs()) {
      System.out.println("상위 디렉토리 생성완료");
    }
    if(file.createNewFile()) {
      System.out.println("파일 생성 완료");
    }
    if(file.delete()) {
      System.out.println("파일 삭제 완료");
    }

    System.out.println("===1=========================");
    // 파일 이름 받기(file.list()) : 밑에가 더 유용함.
    File file2 = new File(".");

    String[] names1 = file2.list(new FilenameFilter() {

      @Override
      public boolean accept(File dir, String name) {
        if (name.contains("a")) {
          return true;
        } else {
          return false;
        }
      }
    });
    for(String name : names1) {
      System.out.println(name);
    }

    System.out.println("==2==========================");
    // 파일 이름 받기(file.listFiles())
    File[] names2 = file2.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if(pathname.getName().contains("a") && pathname.isFile()) {
          return true;
        }
        return false;
      }
    });
    for(File name : names2) {
      System.out.printf("%20s %10s byte\n",name.getName(), name.length());
    }



  }
}
