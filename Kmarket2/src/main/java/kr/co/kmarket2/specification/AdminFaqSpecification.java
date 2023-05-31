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
import kr.co.kmarket2.entity.CsCate2Entity;
import kr.co.kmarket2.entity.CsFaqEntity;

public class AdminFaqSpecification implements Specification<CsFaqEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cate1;
	private int cate2;

	public AdminFaqSpecification(int cate1, int cate2) {
		super();
		this.cate1 = cate1;
		this.cate2 = cate2;
	}

	@Override
	public Predicate toPredicate(Root<CsFaqEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(cate1 != 0) {
			Join<CsFaqEntity, CsCate1Entity> join = root.join("cate1Entity", JoinType.INNER);
			Predicate cate1Predicate = builder.equal(join.get("cate1"), cate1);
			predicates.add(cate1Predicate);
		}

		if(cate2 != 0) {
			Join<CsFaqEntity, CsCate2Entity> join = root.join("cate2Entity", JoinType.INNER);
			Predicate cate2Predicate = builder.equal(join.get("cate2"), cate2);
			predicates.add(cate2Predicate);
		}
//		if(cate2 != 0 && cate1 != 0) {
//			Join<CsCate1Entity, CsCate2Entity> join = root.join("cate2Entity", JoinType.LEFT);
//			Predicate cate2Predicate = builder.equal(join.get("cate1"), cate1);
//			predicates.add(cate2Predicate);
//		}
        if (predicates.size() == 0) {
            return builder.conjunction();
        }
        return builder.and(predicates.toArray(new Predicate[0])); 
	}
}
