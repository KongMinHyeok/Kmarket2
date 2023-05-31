package kr.co.kmarket2.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CsQnaVO {
	private int no;
	private String uid; 
	private int cate1;
	private int cate2;
	private String title;
	private String content;
	private String answer;
	private int status;
	private int hit;
	private String regip;
	private Date rdate;
	private String seller; // 추가 0223 판매자 null 이면 관리자문의, not null 이면 판매자문의
	
	// 추가필드 김지홍
	private String c1Name;
	private String c2Name;
	
	// 추가필드 공민혁
	private int comment;
	
	public int getCate1() {
        return cate1;
    }
    public void setCate1(String cate1) {
        this.cate1 = Integer.parseInt(cate1);
    }
}

