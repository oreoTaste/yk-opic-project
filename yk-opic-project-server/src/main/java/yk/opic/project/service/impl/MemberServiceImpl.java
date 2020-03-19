package yk.opic.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.project.service.MemberService;

public class MemberServiceImpl implements MemberService{
  MemberDao memberDao;

  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public int add(Member member) throws Exception {
    return memberDao.insert(member);
  }

  @Override
  public int delete(int memberNo) throws Exception {
    return memberDao.delete(memberNo);
  }

  @Override
  public Member get(int memberNo) throws Exception {
    return memberDao.findByNo(memberNo);
  }

  @Override
  public List<Member> get(String word) throws Exception {
    return memberDao.findByKeyword(word);
  }

  @Override
  public Member get(String email, String password) throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("email", email);
    map.put("password", password);
    return memberDao.findByEmailAndPassword(map);
  }

  @Override
  public int update(Member member) throws Exception {
    return memberDao.update(member);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

}
