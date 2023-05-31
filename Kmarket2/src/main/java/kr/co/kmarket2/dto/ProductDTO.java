package kr.co.kmarket2.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;
/*
 *  날짜 : 2023/02/15
 *  이름 : 김지홍
 *  내용 : Product DTO
 */
@Data
@Builder
public class ProductDTO {
	private Integer prodNo;
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
	private Integer sold;
	private int delivery;
	private Integer hit;
	private Integer score;
	private Integer review;
	
	private MultipartFile thumb1;
	private MultipartFile thumb2;
	private MultipartFile thumb3;
	private MultipartFile detail;
	private String status;
	private String duty;
	private String receipt;
	private String bizType;
	private String origin;
	private String ip;
	private String rdate;
}
