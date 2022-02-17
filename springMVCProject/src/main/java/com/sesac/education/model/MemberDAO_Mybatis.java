package com.sesac.education.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sesac.vo.MemberVO;



@Repository //@Component 를 상속받고 + DB사용하는 역할
public class MemberDAO_Mybatis {

	@Autowired //type이 같으면 넣어주는 것. Injection 하는 것
	SqlSession session;
	
	final String namespace = "com.sesac.member.";
	
	public List<MemberVO> selectAll(){
		return session.selectList(namespace+"selectAll");
	}
	
	public List<MemberVO> selectById(String id) {
		return session.selectOne(namespace+"selectById", id);
	}
	
	public int insertMember(MemberVO member) {
		return session.insert(namespace+"insert", member);
	}
	
	public int updateMember(MemberVO member) {
		return session.update(namespace+"update", member);
	}
	
	public int deleteMember(String id) {
		return session.delete(namespace+"delete", id);
	}
	
}
