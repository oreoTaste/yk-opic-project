package yk.opic.service.impl;

import java.util.List;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;
import yk.opic.service.MemberService;

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
    return memberDao.findByEmailAndPassword(email, password);
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
