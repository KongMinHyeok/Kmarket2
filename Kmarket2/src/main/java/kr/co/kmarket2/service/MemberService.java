package kr.co.kmarket2.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.MemberDAO;
import kr.co.kmarket2.entity.MemberEntity;
import kr.co.kmarket2.repository.MemberRepo;
import kr.co.kmarket2.vo.MemberTermsVO;
import kr.co.kmarket2.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

	private final MemberRepo memberRepo;
	private final MemberDAO memberDAO;
	private final PasswordEncoder encoder;
	
	public MemberService(MemberRepo memberRepo, MemberDAO memberDAO, PasswordEncoder encoder) {
		this.memberRepo = memberRepo;
		this.memberDAO = memberDAO;
		this.encoder = encoder;
	}
	
	public MemberTermsVO selectTerms() {
		return memberDAO.selectTerms();
	}
	
	// 가입하기
	public void insertMember(MemberVO vo) {
		vo.setPass(encoder.encode(vo.getPass2()));
		if(vo.getCompany() == null) {
			vo.setLevel(1);
			vo.setType(2);
		}else {
			vo.setLevel(5);
			vo.setType(1);
		}
		memberDAO.insertMember(vo);
	};
	
	// uid 중복 체크
	public boolean checkUid(String uid) {
		int result = memberRepo.countByUid(uid);
		return result == 0 ? true : false;
	}
	// email 중복체크
	public boolean checkEmail(String email) {
		int result = memberRepo.countByEmail(email);
		return result == 0 ? true : false;
	}
	// 휴대폰 번호 중복체크
	public boolean checkHp(String hp) {
		int result = memberRepo.countByHp(hp);
		return result == 0 ? true : false;
	}
}
