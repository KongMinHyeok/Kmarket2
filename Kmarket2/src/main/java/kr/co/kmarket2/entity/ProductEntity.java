package kr.co.kmarket2.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
/*
 * 날짜 : 2023/02/16
 * 이름 : 김지홍
 * 내용 : Product Entity
 */
@Data
@Entity
@Table(name = "km_product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {
	@Id
	private int prodNo;
	private int prodCate1;
	private int prodCate2;
	private String prodName;
	private String descript;
	private String company;
	private String seller;
	private int price;
	private int discount;
	private int point;
	private int stock;
	private int sold;
	private int delivery;
	private int hit;
	private int score;
	private int review;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String detail;
	private String status;
	private String duty;
	private String receipt;
	private String bizType;
	private String origin;
	private String ip;
	@CreatedDate
	private LocalDateTime rdate;
}
