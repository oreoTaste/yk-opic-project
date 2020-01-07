package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Member;
import yk.opic.util.ArrayList;

public class MemberHandler {
  ArrayList<Member> memberList;
  public Scanner input;
  
  public MemberHandler(Scanner input) {
    this.input = input;
    memberList = new ArrayList<>();
  }

  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    memberList = new ArrayList<>(capacity);
  }
  
////////////////////////////////////////////////////////////////
  
  public void listMember() {
    Member[] member = new Member[this.memberList.size()];
    memberList.toArray(member);
    for(Member m : member) {
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
  }
}
