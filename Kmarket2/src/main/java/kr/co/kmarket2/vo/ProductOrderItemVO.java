package kr.co.kmarket2.vo;

import lombok.Data;

@Data
public class ProductOrderItemVO {
	private int ordNo;
	private int prodNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
}
