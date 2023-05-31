package kr.co.kmarket2.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "km_cs_cate2")
public class CsCate2Entity {

	@Id
	private int no;
	private int cate1;
	private int cate2;
	private String c2Name;
	
	@OneToMany(mappedBy = "cate2Entity")
	@JsonIgnore
	private List<CsFaqEntity> csFaqEntities;
}
