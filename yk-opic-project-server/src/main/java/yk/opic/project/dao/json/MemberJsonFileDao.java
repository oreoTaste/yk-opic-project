package yk.opic.project.dao.json;

import java.util.List;
import yk.opic.project.dao.MemberDao;
import yk.opic.project.domain.Member;

public class MemberJsonFileDao extends AbstractJsonFileDao<Member> implements MemberDao{

  public MemberJsonFileDao(String fileName) {
    super(fileName);
    loadData();
  }


  @Override
  public int insert(Member member) throws Exception {
    int index = indexOf(member.getNo());
    if(index == 0)
      return 0;

    list.add(member);
    saveData();
    return 1;
  }

  @Override
  public Member findByNo(int no) throws Exception {
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return null;
    }
    saveData();
    return list.get(index);
  }

  @Override
  public List<Member> findAll() throws Exception {
    return list;
  }

  @Override
  public int delete(int no) throws Exception {

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return 0;
    }

    list.remove(index);
    saveData();
    System.out.println("회원을 삭제했습니다.");
    return 1;
  }

  @Override
  public int update(Member member) throws Exception {

    int index = indexOf(member.getNo());

    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return 0;
    }

    Member oldMember = list.get(index);

    if (member.equals(oldMember)) {
      System.out.println("회원 변경을 취소했습니다.");
      return 0;
    } else {
      list.set(index, member);
      saveData();
      System.out.println("회원을 변경했습니다.");
      return 1;
    }
  }

  @Override
  public int indexOf(int no) throws Exception {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no)
        return i;
    }
    return -1;
  }

}
