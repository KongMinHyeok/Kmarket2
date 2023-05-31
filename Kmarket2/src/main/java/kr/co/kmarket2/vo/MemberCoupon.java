package kr.co.kmarket2.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class MemberCoupon {
	
	private String uid; 
	private int id;
	private int status;
	private Date expdate;

}
