package yk.opic.project.dao;

import java.util.List;
import java.util.Map;
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

  default Member findByEmailAndPassword(Map<String, Object> map) throws Exception{
    return null;
  }

}
