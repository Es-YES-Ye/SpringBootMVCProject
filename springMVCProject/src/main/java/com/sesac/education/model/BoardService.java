package com.sesac.education.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sesac.vo.BoardFileVO;
import kr.co.sesac.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	BoardDAO_Mybatis dao; //Mybatis를 이용함
	//BoardDAO dao;       //JDBC 직접 이용함
	
	public List<BoardVO> selectAllBoard(String keyword, String contents) {
		
		return dao.selectAllBoard(keyword, contents);
	}

	public int insertBoard(BoardVO board) {
		
		return dao.insertBoard(board);
	}


	public int viewCnt(int boardNo) {
		
		return dao.viewCnt(boardNo);
	}


	public BoardVO selectBoardByNo(int boardNo) {
		
		return dao.selectBoardByNo(boardNo);
	}

	public int updateBoard(BoardVO board) {
		
		return dao.updateBoard(board);
	}


	public int boardCnt() {
		
		return dao.boardCnt();
	}

	public int insertFile(BoardFileVO fileVO) {
		
		return dao.insertFile(fileVO);
	}

	public List<BoardFileVO> selectFileByNo(int boardNo) {
		
		return dao.selectFileByNo(boardNo);
	}
	
	public int deleteBoardByNo(int boardNo) {
		return dao.deleteBoardByNo(boardNo);
	}
}
