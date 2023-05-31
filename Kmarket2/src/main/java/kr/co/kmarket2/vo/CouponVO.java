package kr.co.kmarket2.vo;


import java.util.Date;

import lombok.Data;

@Data
public class CouponVO {

	private int id;
	private String name;
	private int discount;
	private int condition;
	
	private int status;
	private Date expdate;
}
