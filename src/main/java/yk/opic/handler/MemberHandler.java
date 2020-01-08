package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Member;
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
  
  public void addMember() {
    Member mem = new Member();
    System.out.print("번호? ");
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
  
////////////////////////////////////////////////////////////////
  
  public void listMember() {
    Member[] member = new Member[this.memberList.size()];
    memberList.toArray(member);
    for(Member m : member) {
    System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tF %5$tH:%5$tM:%5$tS\n",
        m.getNo(), m.getName(),  m.getEmail(),  m.getTel() , m.getRegisteredDate() );
    }
  }

  ////////////////////////////////////////////////////////////////

  public void detailMember() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Member mem = this.memberList.get(tempIndex);
    if(mem == null) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }
    
    System.out.printf("번호? %d\n", mem.getNo());
    System.out.printf("이름: %s\n", mem.getName());
    System.out.printf("이메일: %s\n", mem.getEmail());
    System.out.printf("비밀번호: %s\n", mem.getPassword());
    System.out.printf("사진: %s\n", mem.getPhoto());
    System.out.printf("전화: %s\n", mem.getTel());
    System.out.printf("가입일: %1$tF %1$tH:%1$tM:%1$tS\n", mem.getRegisteredDate());
  }

  ////////////////////////////////////////////////////////////////

  public void updateMember() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Member oldValue = this.memberList.get(tempIndex);
    if(oldValue == null) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }

    Member newValue = new Member();
    boolean changed = true;

    System.out.printf("번호? %d\n", oldValue.getNo());
      newValue.setNo(oldValue.getNo());
    
    System.out.printf("이름? (%s) ", oldValue.getName());
    String tempName= input.nextLine();
    if(tempName.length() == 0) {
      changed = false;
      newValue.setName(oldValue.getName());
    }
    newValue.setName(tempName);
    
    System.out.printf("이메일? (%s) ", oldValue.getEmail());
    String tempEmail = input.nextLine();
    if(tempEmail.length() == 0) {
      changed = false;
      newValue.setEmail(oldValue.getEmail());
    }
    newValue.setEmail(tempEmail);
    
    System.out.printf("비밀번호? (%s) ", oldValue.getPassword());
    String tempPassword = input.nextLine();
    if(tempPassword.length() == 0) {
      changed = false;
      newValue.setPassword(oldValue.getPassword());
    }
    newValue.setPassword(tempPassword);
    
    System.out.printf("사진? (%s) ", oldValue.getPhoto());
    String tempPhoto = input.nextLine();
    if(tempPhoto.length() == 0) {
      changed = false;
      newValue.setPhoto(oldValue.getPhoto());
    }
    newValue.setPhoto(tempPhoto);
    
    System.out.printf("전화? (%s) ", oldValue.getTel());
    String tempTel = input.nextLine();
    if(tempTel.length() == 0) {
      changed = false;
      newValue.setTel(oldValue.getTel());
    }
    newValue.setTel(tempTel);

    newValue.setRegisteredDate(new Date(System.currentTimeMillis()));
    
    if(changed) {
      this.memberList.set(tempIndex, newValue);
      System.out.println("회원을 변경했습니다.");
    }
    else System.out.println("회원 변경을 취소했습니다.");
    
  }
  
  public void deleteMember() {
    System.out.print("인덱스 번호? ");
    int tempIndex = input.nextInt();
    input.nextLine();

    Member mem = this.memberList.get(tempIndex);
    if(mem == null) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }

    this.memberList.remove(tempIndex);
    System.out.println("회원을 삭제했습니다.");
  }
  
}
