package com.sesac.education.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sesac.vo.LoginVO;

@Repository
public class LoginDAO {
	
	@Autowired
	 DataSource ds;
	/**
	 * 濡쒓렇�씤 �꽌鍮꾩뒪
	 * @param loginVO (�궗�슜�옄媛� �엯�젰�븳 id, password ���옣)
	 * @return userVO (id,password濡� 議고쉶�맂 �쉶�썝�젙蹂�)
	 * 		   議고쉶�맂 �젙蹂닿� �뾾�떎硫� null 諛섑솚
	 */
	
	public LoginVO login(LoginVO loginVO) {
		// 1.7 try : AutoCloseable �씤�꽣�럹�씠�뒪瑜� �긽�냽諛쏆� �겢�옒�뒪 媛앹껜留� �궗�슜 媛��뒫
		// () �븞�뿉 �꽔�� 蹂��닔�뱾�� �옄�룞�쑝濡� close �샇異쒕맖
		// query�뒗 try臾� 諛뽰뿉 �엳�뼱�빞�븿
		
		LoginVO userVO = null; 
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, password, type ");
		sql.append(" from tbl_member ");
		sql.append(" where id = ? and password = ? ");
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, loginVO.getId());
			pstmt.setString(2, loginVO.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userVO = new LoginVO();
				userVO.setId(rs.getString("id"));
				userVO.setPassword(rs.getString("password"));
				userVO.setType(rs.getString("type"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}
}
