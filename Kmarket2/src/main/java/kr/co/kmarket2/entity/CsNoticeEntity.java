package kr.co.kmarket2.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "km_cs_notice")
public class CsNoticeEntity {
	@Id
	private int no;
//	private int cate1;
	private String title;
	private String content;
	private int hit;
	private String regip;
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date rdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cate1")
	private CsCate1Entity cate1Entity;
}
