package kr.co.kmarket2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * 날짜 : 2023/02/20
 * 이름 : 김지홍
 * 내용 : product cate2 Entity
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "km_product_cate2")
public class ProductCate2Entity {
	@Id
	private int no;
	private int cate1;
	private int cate2;
	private String c2Name;
}
