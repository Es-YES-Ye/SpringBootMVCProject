package com.sesac.education.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sesac.vo.BoardFileVO;
import kr.co.sesac.vo.BoardVO;

@Repository
public class BoardDAO_Mybatis {
	
	@Autowired 
	SqlSession session; //SqlSession: sql 문장을 실행하는 단위: 연결, sql문 실행, 결과받기
	//=prepared statement

	final String namespace = "com.sesac.board.";
	//namespcae 여러번 쓰기 귀찮으니까 상수로 저장하고 사용하기
	
	public List<BoardVO> selectAllBoard(String keyword, String contents) {
		Map<String, String> myMap = new HashMap<>();
		myMap.put("keyword", keyword);
		myMap.put("contents", "%"+contents+"%");
		
		return session.selectList(namespace + "selectAll", myMap); 
		
		//select를 여러 번 해서 list로 자동으로 만들어주고 리턴 바로 된다.
		//("") 안에는 boardMapper에 있는 namespace + id
	}
	
	public int selectBoardNo() {
		return session.selectOne(namespace + "selectBoardNo");	
	}
	
	public int insertBoard(BoardVO board) {
		//int는 리턴타입
		return session.insert(namespace +"insertBoard", board);
		//board 는 파라미터타입 boarMapper.xml에 boardvo 가 parameterType으로 지정되어있다
	}

	public int viewCnt(int boardNo) {
		return session.update(namespace+"viewCnt", boardNo);
	}
	
	public BoardVO selectBoardByNo(int boardNo) {
		return session.selectOne(namespace+"selectBoardByNo", boardNo);
	}

	
	public int deleteBoardByNo(int boardNo) {
		return session.delete(namespace+"deleteBoardByNo", boardNo);
	}
	
	public int updateBoard(BoardVO board) {
		return session.update(namespace+"updateBoard", board);
	}
	
	public int boardCnt() {
		return session.selectOne(namespace+"boardCnt");
	}
	
	public int insertFile(BoardFileVO fileVO) {
		return session.insert(namespace+"insertFile", fileVO);
	}
	
	public List<BoardFileVO> selectFileByNo(int boardNo) {
		return session.selectList(namespace+"selectFileByNo", boardNo);
	}
	
		
}
