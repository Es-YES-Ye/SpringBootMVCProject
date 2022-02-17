package com.sesac.springBootMVCProject.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
@EqualsAndHashCode(of = {"mid"})
@Entity
@Table(name = "tbl2_members") 
@Builder
public class MemberVO {
	
	@Id
	String mid;
	String mpassword;
	String mname;
	@Enumerated(EnumType.STRING)
	MemberRole mrole;

}
