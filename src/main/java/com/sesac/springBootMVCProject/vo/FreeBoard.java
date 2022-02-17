package com.sesac.springBootMVCProject.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_freeboards")
@EqualsAndHashCode(of = "bno")
public class FreeBoard  {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)//oracle:sequence, mysql:identity
	private Long bno;
    @NonNull
    @Column(nullable = false)  
	private String title;
	private String writer;
	@Column(length = 100)
	private String content;	
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;	
	
	//mappedBy -> 나는 이 column에 매여있
	@BatchSize(size = 100)
	@OneToMany(mappedBy = "board", 
			  cascade = CascadeType.ALL  , 
			  fetch = FetchType.EAGER
			  )	//fetch는 select 라는 뜻	//나를 참조한다고 해서... 어쩌고... 안 하겠다.
		List<FreeBoardReply> replies;
	
}


