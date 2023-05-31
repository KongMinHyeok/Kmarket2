package kr.co.kmarket2.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CsFaqVO {
	private int no;
	private int cate1;
	private int cate2;
	private String title;
	private String content;
	private int hit;
	private String regip;
	private LocalDateTime rdate;
}
