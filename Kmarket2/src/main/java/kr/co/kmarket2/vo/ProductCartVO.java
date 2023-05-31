package kr.co.kmarket2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class ProductCartVO {
	private int cartNo;
	private String uid;
	private int prodNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	private String rdate;
	//추가필드
	private String prodName;
	private String thumb1;
	private String descript;
	private int prodCate1;
	private int prodCate2;
}
