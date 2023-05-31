package kr.co.kmarket2.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "km_cs_cate1")
public class CsCate1Entity {

	@Id
	private int cate1;
	private String c1Name;
	
//	@ManyToOne
//	@JoinColumn(name = "csNoticeEntity_id")
//	private CsNoticeEntity csNoticeEntity;
	@OneToMany(mappedBy = "cate1Entity", fetch = FetchType.LAZY)
	private List<CsNoticeEntity> csNoticeEntities;
	
	@OneToMany(mappedBy = "cate1Entity", fetch = FetchType.LAZY)
	private List<CsFaqEntity> csFaqEntities;
}
