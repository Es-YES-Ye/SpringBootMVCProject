package com.sesac.springBootMVCProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sesac.springBootMVCProject.vo.BoardVO;

//interface설계 CrudRepository 를 상속 받은 PagingAndSortingRepository이다. 그래서
//PagingAndSortingRepository에는 CrudRepository 기능도 있다.
public interface BoardRepository extends PagingAndSortingRepository<BoardVO, Long>{
	//entity는 BoardVO
	
	//기본: findAll(), findById(), save(),
	//delete(), count()
	
	
	//규칙에 맞는 메서드 추가한다.
	List<BoardVO> findByTitle(String title);
	//Iterable 로 할 수도 있음 collection이기만 하면 된다.
	List<BoardVO> findByWriter(String writer);
	List<BoardVO> findByContent(String content);
	List<BoardVO> findByTitleAndWriter(String title, String writer);
	List<BoardVO> findByBnoBetween(Long bno, Long bno2);
	List<BoardVO> findByContentLike(String content);	
	List<BoardVO> findByContentContaining(String content);	
	List<BoardVO> findByContentContainingAndBnoGreaterThan(String content, Long bno);	
	List<BoardVO> findByContentContainingAndBnoGreaterThanOrderByBnoDesc(String content, Long bno);	
	List<BoardVO> findByWriterStartingWith(String writer);	
	
	long countByWriter(String writer);

	
	//paging 추가
	List<BoardVO> findByContentContainingAndBnoGreaterThanOrderByBnoDesc(String content, Long bno, Pageable paging);
	List<BoardVO> findByContentContaining(String content, Pageable paging);	
	//data 정보만 return 한다
	
	Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable paging);
	//결과를 페이지로 리턴... 3 페이지 갈지 5페이지 갈지....
	//page 정보와 data를 return 한다.
	
	
	//sql문과 비슷하다
	//JPQL (JPA Query Language) ....JPA 가 자동생성되는 함수로는 한계가 있다.
	@Query("select b from BoardVO b where title like %?1% and b.bno > ?2 order by b.bno desc")
	List<BoardVO> findByTitle2(String title, Long bno);
	
	@Query("select b from BoardVO b where b.title like %:title% and b.bno > :bno2 order by b.bno desc")
	List<BoardVO> findByTitle3(@Param("bno2")Long bno, @Param("title")String title);

	@Query("select b from #{#entityName} b where b.title like %:title% and b.bno > :bno2 order by b.bno desc")
	List<BoardVO> findByTitle4(@Param("bno2")Long bno, @Param("title")String title);
	
	@Query(value = "select * from tbl2_boards where title like %?2% and bno > ?1 order by bno desc", nativeQuery = true)
	List<BoardVO> findByTitle5(Long bno, String title);
	
	
}
