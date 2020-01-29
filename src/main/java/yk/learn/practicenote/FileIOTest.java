package yk.learn.practicenote;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {
  public static void main(String[] args) throws IOException {
    File file10 = new File("src/main/java/yk/learn/practicenote/test1.txt");
    
    FileOutputStream out10 = new FileOutputStream(file10.getPath());
    byte[] b10 = new byte[] {'A', 'B', 'C', 0x30, 0x50, 0x15};
    out10.write(b10);
    System.out.println("입력완료");
    
    FileInputStream in10 = new FileInputStream(file10.getPath());
    byte[] b11 = new byte[100];
    int count10 = in10.read(b11);
    System.out.println("총 개수 : " + count10);
    System.out.print("==> ");
    for(int i = 0 ; i < count10 ; i++) {
      System.out.printf("%x ", b11[i]);
    } System.out.println();
    out10.close(); //not mandatory
    in10.close(); //not mandatory
    
    System.out.println("======================================");
    
    File file20 = new File("src/main/java/yk/learn/practicenote/test2.txt");
    BufferedOutputStream out20 = new BufferedOutputStream(new FileOutputStream(file20.getPath()));
    byte[] byte20 = new byte[] {'A', 'B', 'C', 0x30, 0x50, 0x15};
    out20.write(byte20);
    System.out.println("입력완료");
    out20.close();

    BufferedInputStream in20 = new BufferedInputStream(new FileInputStream(file20.getPath()));
    byte[] byte21 = new byte[100];
    int count20 = in20.read(byte21);
    System.out.println("총 개수 : " + count20);
    System.out.print("==> ");
    for(int i = 0 ; i < count20 ; i++) {
      System.out.printf("%x ", byte21[i]);
    } System.out.println();
    in20.close(); //not mandatory
    
    
    System.out.println("======================================");
    
    File file30 = new File("src/main/java/yk/learn/practicenote/test3.txt");
    FileWriter out30 = new FileWriter(file30.getPath());
    char[] cbuf = new char[] {'A', 'B', 'C', '가', '각', '간', '히'};
    out30.write(cbuf);
    System.out.println("입력완료");
    out30.close(); // or flush()
    
    FileReader in30 = new FileReader(file30.getPath());
    char[] buf30 = new char[100];
    int count30 = in30.read(buf30);
    System.out.println("총 개수 : " + count30);
    
    System.out.print("==> ");
    for(int i = 0; i < count30 ; i++) {
      System.out.printf("%c(%x) ", buf30[i], (int)buf30[i]);
    }
    System.out.println();
    
    String str = String.valueOf(buf30);
    System.out.print("==> ");
    System.out.println("String.valueOf : " + str);
    in30.close(); //not mandatory
    
    System.out.println("======================================"); //33879
    
    File file40 = new File("docsForTest/jls11.pdf");
    FileInputStream in40 = new FileInputStream(file40.getPath());
    FileOutputStream out40 = new FileOutputStream(
        file40.getPath().replace("jls11.pdf", "jls11_1.pdf"));

    long startTime = System.currentTimeMillis();
    int b;
    while((b = in40.read()) != -1 ) {
      out40.write(b);
    }
    long endTime = System.currentTimeMillis();
    in40.close(); // not mandatory
    out40.close(); // not mandatory
    System.out.println("Copying Time Spent (using FileI/OStream):" + (endTime - startTime));
    
    System.out.println("======================================"); // 100
    
    File file50 = new File("docsForTest/jls11.pdf");
    BufferedInputStream in50 = new BufferedInputStream(
        new FileInputStream(file50.getPath()));
    BufferedOutputStream out50 = new BufferedOutputStream(
        new FileOutputStream(file50.getPath().replace("jls11.pdf", "jls11_1.pdf")));

    long startTime2 = System.currentTimeMillis();
    int buf;
    while((buf = in50.read()) != -1 ) {
      out50.write(buf);
    }
    long endTime2 = System.currentTimeMillis();
    in50.close();
    out50.close();
    System.out.println("Copying Time Spent (using BufferedI/OStream):" + (endTime2 - startTime2));
    
  }
}
