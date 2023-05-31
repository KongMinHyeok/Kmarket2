package kr.co.kmarket2.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import kr.co.kmarket2.entity.CsCate1Entity;
import kr.co.kmarket2.entity.CsNoticeEntity;

public class AdminNoticeSpecification implements Specification<CsNoticeEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cate1;
	
	public AdminNoticeSpecification(int cate1) {
		this.cate1 = cate1;
	}

	@Override
	public Predicate toPredicate(Root<CsNoticeEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(cate1 != 0) {
			Join<CsNoticeEntity, CsCate1Entity> join = root.join("cate1Entity", JoinType.LEFT);
			Predicate cate1Predicate = builder.equal(join.get("cate1"), cate1);
			predicates.add(cate1Predicate);
		}
        if (predicates.size() == 0) {
            return builder.conjunction();
        }
        return builder.and(predicates.toArray(new Predicate[0])); 
	}
}
