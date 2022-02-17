package com.sesac.springBootMVCProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sesac.springBootMVCProject.vo.FreeBoard;
//Wrapper class
//int->Integer
//long->Long
//	PagingAndSortingRepository ==> 기본에 다음이 제공된다. findAll(), boardRepo.findAll(pageable paging);
// CrudRepository ==> 기본에 다음이 제공된다. boardRepo.findAll();
public interface FreeBoardRepository extends PagingAndSortingRepository<FreeBoard, Long> {
	List<FreeBoard> findByTitleContaining(String title);
	//특정작성자가 작성한 Board정보를 페이지 정보와 함께 return하기
	//List로 바꾸면 다음 페이지는 알 수 없다. Page<>로 해야 다음페이지 정보까지 전부 가져온다
	Page<FreeBoard> findByWriter(String writer, Pageable paging);
	
}
