package yk.opic.handler;

import java.sql.Date;
import java.util.Scanner;
import yk.opic.domain.Lesson;
import yk.opic.domain.Member;
import yk.opic.domain.Member;
import yk.opic.util.ArrayList;
import yk.opic.util.Prompt;

public class MemberHandler {
  ArrayList<Member> memberList;
  public Scanner input;
  Prompt prompt;

  public MemberHandler(Prompt prompt) {
    this.prompt = prompt;
    memberList = new ArrayList<>();
  }

  public MemberHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    memberList = new ArrayList<>(capacity);
  }

  ////////////////////////////////////////////////////////////////

  public void addMember() {

    Member member = new Member();

    member.setNo(prompt.inputInt("번호?"));
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("비밀번호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
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

    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);

    if(index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }

    Member member = this.memberList.get(index);

    try {
      System.out.printf("번호? %d\n", member.getNo());
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("비밀번호: %s\n", member.getPassword());
      System.out.printf("사진: %s\n", member.getPhoto());
      System.out.printf("전화: %s\n", member.getTel());
      System.out.printf("가입일: %1$tF %1$tH:%1$tM:%1$tS\n", member.getRegisteredDate());
    } catch (Exception e) {
      return;
    }
  }

  ////////////////////////////////////////////////////////////////

  public void updateMember() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);

    if(index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }

    Member oldMember = this.memberList.get(index);
    Member newMember = new Member();

    newMember.setNo(oldMember.getNo());

    newMember.setName(prompt.inputString(
        String.format("이름? (%s) ", oldMember.getName()),
        oldMember.getName()));

    newMember.setEmail(prompt.inputString(
        String.format("이메일? (%s) ", oldMember.getEmail()),
        oldMember.getEmail()));

    newMember.setPassword(prompt.inputString(
        String.format("비밀번호? (%s) ", oldMember.getPassword()),
        oldMember.getPassword()));

    newMember.setPhoto(prompt.inputString(
        String.format("사진? (%s) ", oldMember.getPhoto()),
        oldMember.getPhoto()));

    newMember.setTel(prompt.inputString(
        String.format("전화? (%s) ", oldMember.getTel()),
        oldMember.getTel()));

    newMember.setRegisteredDate(new Date(System.currentTimeMillis()));

    if(newMember.equals(oldMember)) {
      System.out.println("회원 변경을 취소했습니다.");
    } else {
      this.memberList.set(index, newMember);
      System.out.println("회원을 변경했습니다.");
    }
  }


  public void deleteMember() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);

    if(index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
    }

    this.memberList.remove(index);
    System.out.println("회원을 삭제했습니다.");
  }


  public int indexOfMember(int no) {
    for(int i = 0 ; i < this.memberList.size() ; i++) {
      if(memberList.get(i).getNo() == no)
        return i;
    } return -1;
  }

}
