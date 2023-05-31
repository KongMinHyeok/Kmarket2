package kr.co.kmarket2.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import kr.co.kmarket2.entity.CsNoticeEntity;

public interface CsNoticeRepo extends JpaRepository<CsNoticeEntity, Integer>, JpaSpecificationExecutor<CsNoticeEntity>{
	Page<CsNoticeEntity> findAllByOrderByRdateDesc(Pageable pageable);
	
	
//	@Query(value = "SELECT c FROM CsNoticeEntity c LEFT JOIN FETCH c.cate1Entity")
//	List<CsNoticeEntity> findAllWithCsNoticeEntities();
	
//	@Query(value = "SELECT n FROM CsNoticeEntity n LEFT JOIN FETCH n.cate1Entity c", countQuery = "SELECT COUNT(n) FROM CsNoticeEntity n")
//	Page<CsNoticeEntity> findAllWithCsCate1Entities(Specification<CsNoticeEntity> specification, Pageable pageable);
	
	 Page<CsNoticeEntity> findAll(Specification<CsNoticeEntity> specification, Pageable pageable);
	
}
