package com.sesac.springBootMVCProject.vo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_pdsboard")
@EqualsAndHashCode(of = "pid")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PDSBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	private String pname;
	private String pwriter;

	//cascade 내 변화를 연결해주겠다...
	//fetch 는 원래 lazy 내가 조회했는데 pdsfile도 같이 따라오면 eager
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pdsno") // PDSFile칼럼에 생성
	//joincolumn 이름이 pdsfile에 들어간다
	private List<PDSFile> files2;

	
}