package kr.co.kmarket2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kmarket2.entity.ProductCate2Entity;

public interface ProductCate2Repo extends JpaRepository<ProductCate2Entity, Integer>{
	List<ProductCate2Entity> findByCate1(int cate1);
}
