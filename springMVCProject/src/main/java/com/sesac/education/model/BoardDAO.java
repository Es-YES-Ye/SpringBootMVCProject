package com.sesac.education.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sesac.education.util.JDBCClose;

import kr.co.sesac.vo.BoardFileVO;
import kr.co.sesac.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Autowired //type이 같으면 넣어라(injection)
	DataSource ds;

	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, title, writer, to_char(reg_date, 'yyyy-mm-dd') as reg_date, view_cnt ");
			sql.append(" from tbl_board ");
			sql.append(" order by no desc ");
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()){
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String regDate = rs.getString("reg_date");
				int viewCnt = rs.getInt("view_cnt");
				BoardVO board = new BoardVO(no, title, writer, regDate);
				board.setViewCnt(viewCnt);
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(pstmt, conn);
		}
		return list;
	}
	
	/**
	 * 寃뚯떆臾쇰쾲�샇 異붿텧(seq_tbl_board_no)
	 */
	public int selectBoardNo() {
		String sql = "select seq_tbl_board_no.nextval from dual ";
		int boardNo = 0;
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			boardNo = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardNo;
	}
	
	/**
	 * �깉湲��벑濡�
	 */
	public int insertBoard(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = ds.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tbl_board(no, title, writer, content) ");
			sql.append(" values(seq_tbl_board_no.nextval, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			//pstmt.setInt(1, board.getNo());
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(pstmt, conn);
		}
		return result;
	}
	
	/**
	 * 議고쉶�닔 利앷�
	 */
	public int viewCnt(int boardNo) {
		int result = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update tbl_board set view_cnt = view_cnt + 1 where no = ?");
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 寃뚯떆湲� �긽�꽭 蹂닿린
	 * @param boardNo
	 * @return
	 */
	public BoardVO selectBoardByNo(int boardNo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select no, title, writer, content, view_cnt, to_char(reg_date, 'yyyy-mm-dd') reg_date ");
		sql.append(" from tbl_board where no = ? ");
		BoardVO board = null;
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer"); 
				String content = rs.getString("content");
				int viewCnt = rs.getInt("view_cnt");
				String regDate = rs.getString("reg_date");
				board = new BoardVO(no, title, writer, content, viewCnt, regDate);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	/**
	 * 寃뚯떆臾� �닔�젙 (�젣紐�, �옉�꽦, �궡�슜)
	 */
	public int updateBoard(BoardVO board) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update tbl_board set title = ?, writer = ?, content = ? where no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getNo());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(pstmt, conn);
		}
		return result;
	}
	
	/**
	 * �럹�씠吏�
	 */
	public int boardCnt() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(no) as no from tbl_board ");
		int boardCnt = 0;
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			boardCnt = rs.getInt("no");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardCnt;
	}
	
	/**
	 * 泥⑤��뙆�씪 CRUD
	 */
	public int insertFile(BoardFileVO fileVO) {
		
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tbl_board_file(no, board_no, file_ori_name, file_save_name, file_size) ");
		sql.append(" values(seq_tbl_board_file_no.nextval, ?, ?, ?, ?) ");
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());		
		){
			pstmt.setInt(1, fileVO.getBoardNo());
			pstmt.setString(2, fileVO.getFileOriName());
			pstmt.setString(3, fileVO.getFileSaveName());
			pstmt.setInt(4, fileVO.getFileSize());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<BoardFileVO> selectFileByNo(int boardNo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select no, file_ori_name, file_save_name, file_size ");
		sql.append(" from tbl_board_file where board_no = ? ");
		List<BoardFileVO> fileList = new ArrayList<>();
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			pstmt.setInt(1, boardNo);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setNo(rs.getInt("no"));
				fileVO.setFileOriName(rs.getString("file_ori_name"));
				fileVO.setFileSaveName(rs.getString("file_save_name"));
				fileVO.setFileSize(rs.getInt("file_size"));
				
				fileList.add(fileVO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileList;
	}
	
	
	
	/*
	 * 
	 * delete 
	 */
	
	public int deleteBoardByNo(int boardNo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from tbl_board where no = ? ");
		int result = 0;
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
