package kr.co.kmarket2.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;

import kr.co.kmarket2.entity.ProductEntity;
/*
 * 날짜 : 2023/02/16
 * 이름 : 김지홍
 * 내용 : JPA 의 Product Repository
 */
public interface ProductRepo extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity>{
	@Modifying
	@Transactional
	void deleteAllByProdNoIn(int[] checks);
	void deleteByProdNo(int prodNo);
}
