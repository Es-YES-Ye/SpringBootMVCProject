package com.sesac.education.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sesac.education.util.JDBCClose;

import kr.co.sesac.vo.MemberVO;



@Repository //@Component 를 상속받고 + DB사용하는 역할
public class MemberDAO {

	@Autowired //type이 같으면 넣어주는 것. Injection 하는 것
	DataSource datsSource;
	
	public List<MemberVO> selectAll(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<MemberVO> list = new ArrayList<>();
		
		try {
			conn = datsSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, email_id, email_domain, tel1, tel2, tel3, post, ");
			sql.append(" basic_addr, detail_addr, type, to_char(reg_date, 'yyyy-mm-dd') as reg_date from tbl_member");
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				String eId = rs.getString("email_id");
				String eDomain = rs.getString("email_domain");
				String tel1 = rs.getString("tel1");
				String tel2 = rs.getString("tel2");
				String tel3 = rs.getString("tel3");
				String post = rs.getString("post");
				String bAddr = rs.getString("basic_addr");
				String dAddr = rs.getString("detail_addr");
				String type = rs.getString("type");
				String regDate = rs.getString("reg_date");

			//	MemberVO member = new MemberVO(id, name, eId, eDomain, tel1, tel2, tel3, post, bAddr, dAddr, type, regDate);
				//list.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(pstmt, conn);
		}
		return list;
	}
	
	public void singUp(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = datsSource.getConnection();
			StringBuilder sql2 = new StringBuilder();
			sql2.append("select * from tbl_member");
			pstmt2 = conn.prepareStatement(sql2.toString());
			pstmt2.executeQuery();
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tbl_member(id, name, password, email_id, email_domain, tel1, tel2, tel3, ");
			sql.append(" post, basic_addr, detail_addr) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail_id());
			pstmt.setString(5, member.getEmail_domain());
			pstmt.setString(6, member.getTel1());
			pstmt.setString(7, member.getTel2());
			pstmt.setString(8, member.getTel3());
			pstmt.setString(9, member.getPost());
			pstmt.setString(10, member.getBasic_addr());
			pstmt.setString(11, member.getDetail_addr());
			
			pstmt.executeUpdate();
		}catch (SQLIntegrityConstraintViolationException s) {
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCClose.close(pstmt, conn);
		}
	}
}
