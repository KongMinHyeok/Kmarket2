package kr.co.kmarket2.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductVO {
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
	private String rdate;

	private int etc1;
	private int etc2;
	private String etc3;
	private String etc4;
	private String etc5;
	
	//추가 필드
	private String c1Name;
	private int cate1;
	private int cate2;
	private String c2Name;
}
