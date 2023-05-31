package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.MemberTermsVO;
import kr.co.kmarket2.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {

	public List<MemberVO> selectMembers();
	public MemberVO selectMember(String uid);
	public void insertMember(MemberVO vo);
	public void updateMember(MemberVO vo);
	public void deleteMember(String uid);
	
	public MemberTermsVO selectTerms();
	
	int countUid(String uid);
}
