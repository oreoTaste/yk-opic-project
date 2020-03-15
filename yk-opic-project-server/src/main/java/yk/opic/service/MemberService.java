package yk.opic.service;

import java.util.List;
import yk.opic.project.domain.Member;

public interface MemberService {

  int add(Member member) throws Exception;

  int delete(int memberNo) throws Exception;

  Member get(int memberNo) throws Exception;

  List<Member> get(String word) throws Exception;

  Member get (String email, String password) throws Exception;

  int update(Member member) throws Exception;

  List<Member> list() throws Exception;
}
