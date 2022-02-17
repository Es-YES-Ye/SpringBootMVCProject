package com.sesac.springBootMVCProject;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sesac.springBootMVCProject.repository.BoardRepository;
import com.sesac.springBootMVCProject.vo.BoardVO;

@SpringBootTest
public class BoardTest {
	
//interface 설계
	@Autowired
	BoardRepository bRepo;
	//@Test//조회
	
	@Test
	public void jpqlTest1() {
		bRepo.findByTitle5(10L,"화려한3").forEach(b->{
			System.out.println(b);
		});
	}
	
//	@Test
	public void pageTest3() {
		Pageable paging = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<BoardVO> result = bRepo.findByBnoGreaterThan(10L, paging);
		//페이지 정보를 알아야하니까 page 타입으로 리턴한다.
		
		List<BoardVO> blist = result.getContent();
		System.out.println("page size : " + result.getSize());
		System.out.println("page TotalElements : " + result.getTotalElements());
		System.out.println("page TotalPage : " + result.getTotalPages());
		System.out.println("다음 Page 정보 : " + result.nextPageable());
		System.out.println("nextOrLastPageable : " + result.nextOrLastPageable());
		
		blist.forEach(b->{
			System.out.println(b);
		});
	}
	
	
//	@Test
	public void pageTest2() {
		IntStream.range(0, 10).forEach(p->{
			System.out.println("=======================================================페이지  " + p + "================================================" );
			Pageable paging = PageRequest.of(p, 10, Sort.by("bno").descending());
			bRepo.findAll(paging).forEach(b->{
				System.out.println(b);
			});			
		});
		
	}
	
//	@Test
	public void pageTest1() {
		String content = "Spring";
		Pageable paging = PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "bno"));	
		bRepo.findByContentContaining(content, paging).forEach(b->{
			
			System.out.println(b);
		});;
	}
	
//	@Test
	public void test33() {
		String content= "Spring";
		Long bno = 10L;
		
		bRepo.findByContentContainingAndBnoGreaterThanOrderByBnoDesc(content, bno).forEach(b->{
			System.out.println(b);
		});;	
		}
	
	
//	@Test
	public void findByWriterStartingWith() {
		String writer1 = "선우";
		String writer2 = "제갈";
		String writer3 = "독고";

		List<BoardVO> blist1 = bRepo.findByWriterStartingWith(writer1);
		List<BoardVO> blist2 = bRepo.findByWriterStartingWith(writer2);
		List<BoardVO> blist3 = bRepo.findByWriterStartingWith(writer3);

		if(blist1.size()!=0) {
			System.out.println("선우로 시작하는 작성자 : " + blist1 );
		}else {
			System.out.println("제갈로 시작하는 작성자 : " + blist2 );
			System.out.println("==========================================================================================================");
			System.out.println("독고로 시작하는 작성자 : " + blist3 );
				}	
		}
	
	
//	@Test
	public void findByContentContainingAndBnoGreaterThan() {
		List<BoardVO> blist = bRepo.findByContentContainingAndBnoGreaterThan("SpringBoot 8", 50L);
		for(BoardVO board:blist) {
			System.out.println(board);			
		}
	}	

//	@Test
	public void countByWriter() {
		String writer = "제갈남궁3";
		long count = bRepo.countByWriter(writer);
		System.out.println(count);
	}	
	
	
//	@Test
	public void findByContentContaining() {
		String content = "SpringBoot 8";
		bRepo.findByContentContaining(content).forEach(board->{
			System.out.println(board);
		});
	}
	
//	@Test
	public void findByContentLike() {
		String content = "%SpringBoot 9%";
		bRepo.findByContentLike(content).forEach(board->{
			System.out.println(board);
		});
	}
	
	
//	@Test
	public void findByBnoBetween() {
		bRepo.findByBnoBetween(10L, 15L).forEach(board->{
			System.out.println(board);
		});
	}
	
	
//	@Test
	public void findByTitleAndWriter() {
		String title = "도시를 벗어나";
		String writer = "제갈남궁7";
		bRepo.findByTitleAndWriter(title, writer).forEach(board->{
			System.out.println(board);
		});
	}
	
	
//	@Test
	public void findByContent() {
		String content = "빛이 나를 감싸는 SpringBoot 6";
		bRepo.findByContent(content).forEach(board->{
			System.out.println(board);
		});
	}
	
	
//	@Test
	public void findByWriter() {
		String writer = "제갈남궁7";
		bRepo.findByWriter(writer).forEach(board->{
			System.out.println(board);
		});
	}
	
	
	
//	@Test
	public void findByTitle() {
		String title = "도시를 벗어나";
		bRepo.findByTitle(title).forEach(board->{
			System.out.println(board);
		});
	}
	
//	@Test//카운트
	public void boardCount() {
		long cnt = bRepo.count();
		System.out.println("전체 건수 : " + cnt);
	}
	
	
//	@Test//삭제
	public void boardDelete() {
		Long bno = 11L;
		bRepo.deleteById(bno);
	}
	
	
	
//	@Test//수정
	public void boardUpdate() {
		Long bno = 10L;
		bRepo.findById(bno).ifPresent(board->{
			board.setTitle("도시를 벗어나");
			board.setWriter("독고제갈");
			board.setContent("화려한 불빛을 떠나");
			bRepo.save(board);//update set
		});
	}
	
	//@Test//조회
	public void test3() {
		int i = 10;
		Long bno = 100000L;
		
		//있다면 찍어라... board에 넣어서.
		bRepo.findById(bno).ifPresent(board->{
			System.out.println("-----------------------------------------------------");
			System.out.println(board);
		});
		
		System.out.println("**********************************************************");
		
		//자바스크립트 화살표 함수, 람다식
		bRepo.findById(bno).ifPresentOrElse(board->{
			System.out.println("찾음 : " + board);
		}, ()->{
			System.out.println(bno + "는 존재하지 않는 게시 번호입니다.");
		});
	}
	
	
	
//	@Test//전부조회
	public void test2() {
		bRepo.findAll().forEach(board->{
		System.out.println(board);	
		});
	}
	
//	@Test
	public void test1() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			BoardVO board = BoardVO.builder()
					.title("화려한" + i)
					.writer("제갈남궁" + i%10)
					.content("빛이 나를 감싸는 SpringBoot " + i%10)
					.build();
			bRepo.save(board);
		});
	}

	
	
}
