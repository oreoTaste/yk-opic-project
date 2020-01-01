package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Member;

public class MemberHandler {
  MemberList memberList;
  public Scanner input;
  
  public MemberHandler(Scanner input) {
    this.input = input;
    memberList = new MemberList();
  }

  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    memberList = new MemberList(capacity);
  }
  
////////////////////////////////////////////////////////////////
  
  public void listMember() {
    Member[] mbr = memberList.toArray();
    for(Member m : mbr) {
    System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
        m.getNo(), m.getName(),  m.getEmail(),  m.getTel() , m.getRegisteredDate() );
    }
  }
  
////////////////////////////////////////////////////////////////

  public void addMember() {
    Member mem = new Member();
    System.out.print("번호?");
    mem.setNo(input.nextInt());
    input.nextLine(); // 빈칸제거
    System.out.print("이름? ");
    mem.setName(input.nextLine());
    System.out.print("이메일? ");
    mem.setEmail(input.nextLine());
    System.out.print("비밀번호? ");
    mem.setPassword(input.nextLine());
    System.out.print("사진? ");
    mem.setPhoto(input.nextLine());
    System.out.print("전화? ");
    mem.setTel(input.nextLine());
    mem.setRegisteredDate(new Date(System.currentTimeMillis()));
    
    memberList.add(mem);
    System.out.println("저장하였습니다.");
  }
}
