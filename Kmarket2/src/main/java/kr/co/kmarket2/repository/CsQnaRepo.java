package kr.co.kmarket2.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import kr.co.kmarket2.entity.CsQnaEntity;

public interface CsQnaRepo extends JpaRepository<CsQnaEntity, Integer>{
	@Modifying
	@Transactional
	void deleteAllByNoIn(int[] ids);
}
