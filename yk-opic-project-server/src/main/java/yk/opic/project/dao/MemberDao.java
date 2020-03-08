package yk.opic.project.dao;

import java.util.List;
import yk.opic.project.domain.Member;

public interface MemberDao {

  int insert(Member member) throws Exception;

  Member findByNo(int no) throws Exception;

  List<Member> findAll() throws Exception;

  int delete(int no) throws Exception;

  int update(Member member) throws Exception;

  default List<Member> findByKeyword(String word) throws Exception{
    return null;
  }

  default Member findByEmailAndPassword(String email, String password) throws Exception{
    return null;
  }

}
