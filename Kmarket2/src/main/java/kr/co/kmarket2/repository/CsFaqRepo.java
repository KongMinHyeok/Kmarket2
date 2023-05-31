package kr.co.kmarket2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import kr.co.kmarket2.entity.CsFaqEntity;

public interface CsFaqRepo extends JpaRepository<CsFaqEntity, Integer>, JpaSpecificationExecutor<CsFaqEntity>{

}
