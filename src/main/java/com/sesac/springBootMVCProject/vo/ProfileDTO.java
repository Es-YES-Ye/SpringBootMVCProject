package com.sesac.springBootMVCProject.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"fno"})
@Entity
@Table(name = "tbl2_profile")
@Builder
public class ProfileDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long fno;
	String fname;
	boolean currentYn;

	//profile 여러 개가 하나의 member 소유이다.
	@ManyToOne
	//멤버 하나가 profile 여러 개 있는 것.
	//나 중심이다. 여기서 나는 profile
	MemberVO member;
	//profile이 member를 참조하고
	//컬럼 이름은 member_mid 이렇게 들어온다.
}
