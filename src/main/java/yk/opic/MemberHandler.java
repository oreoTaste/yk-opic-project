package yk.opic;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Member;

public class MemberHandler {
  
  static final int SIZE = 100;
  static int memberCount = 0;
  static Member[] member = new Member[SIZE];
  static Scanner scanner;
  
  static void listMember() {
    for(int i=0 ; i<memberCount ; i++) {
      Member m = member[i];
      System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
          m.no, m.name,  m.email,  m.tel , m.registeredDate );
    }
  }
  

  static void addMember() {
    Member mem = new Member();
    System.out.print("번호?");
    mem.no = scanner.nextInt();
    scanner.nextLine(); // 빈칸제거
    System.out.print("이름? ");
    mem.name = scanner.nextLine();
    System.out.print("이메일? ");
    mem.email = scanner.nextLine();
    System.out.print("비밀번호? ");
    mem.password = scanner.nextLine();
    System.out.print("사진? ");
    mem.photo = scanner.nextLine();
    System.out.print("전화? ");
    mem.tel = scanner.nextLine();
    mem.registeredDate = new Date(System.currentTimeMillis());
    
    member[memberCount++] = mem;
    System.out.println("저장하였습니다.");
  }
}
