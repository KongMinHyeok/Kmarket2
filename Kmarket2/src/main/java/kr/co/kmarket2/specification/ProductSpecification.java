package kr.co.kmarket2.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import kr.co.kmarket2.entity.ProductEntity;

public class ProductSpecification implements Specification<ProductEntity>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prodName;
	private String prodNo;
	private String company;
	private String seller;

	public ProductSpecification(String prodName, String prodNo, String company, String seller) {
        this.prodName = prodName;
        this.prodNo = prodNo;
        this.company = company;
        this.seller = seller;
    }

	@Override
	public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();

        if (prodName != null && !prodName.isEmpty()) {
            String pattern = "%" + prodName + "%";
            Predicate namePredicate = builder.like(root.get("prodName"), pattern);
            predicates.add(namePredicate);
        }

        if (prodNo != null && !prodNo.isEmpty()) {
            Predicate noPredicate = builder.equal(root.get("prodNo"), prodNo);
            predicates.add(noPredicate);
        }

        if (company != null && !company.isEmpty()) {
            Predicate companyPredicate = builder.equal(root.get("company"), company);
            predicates.add(companyPredicate);
        }
        
        if (seller != null && !seller.isEmpty()) {
            Predicate sellerPredicate = builder.equal(root.get("seller"), seller);
            predicates.add(sellerPredicate);
        }

        if (predicates.size() == 0) {
            return builder.conjunction();
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
