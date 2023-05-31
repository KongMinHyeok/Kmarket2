package kr.co.kmarket2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kmarket2.entity.CsCate2Entity;

public interface CsCate2Repo extends JpaRepository<CsCate2Entity, Integer>{

	List<CsCate2Entity> findByCate1(int cate1);

}
