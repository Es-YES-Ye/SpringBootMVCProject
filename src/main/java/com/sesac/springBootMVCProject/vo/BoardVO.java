package com.sesac.springBootMVCProject.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl2_boards")//name 의 이름으로 테이블을 만든다
@Entity
public class BoardVO {
	
	@Id//primary key 설정
	@GeneratedValue(strategy = GenerationType.AUTO) 
	//자동으로 증가(DB에 맞게 자동으로...)
	//oracle은 sequence를 사용 이름: hibernate_sequence
	private Long bno;
	@NonNull //자바에서 객체 생성시 필수이다.
	@Column(nullable = false) //DB에서 not null로 설정
	private String title;
	private String writer;
	@Column(length = 100)
	private String content;
	@CreationTimestamp //insert할 때 시간 자동으로 들어감, 생성시에만 입력된다
	private Timestamp regdate;
	@UpdateTimestamp //생성시에 입력되고 수정시에 수정됨다
	private Timestamp updatedate;
}
