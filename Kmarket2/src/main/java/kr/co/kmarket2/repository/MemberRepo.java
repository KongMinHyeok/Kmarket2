package kr.co.kmarket2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kmarket2.entity.MemberEntity;
/*
 * 날짜 : 2023/02/16
 * 이름 : 김지홍
 * 내용 : JPA 의 Member Repository
 */
public interface MemberRepo extends JpaRepository<MemberEntity, String>{
	int countByUid(String uid);
	int countByEmail(String email);
	int countByHp(String hp);
}
