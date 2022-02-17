package com.sesac.springBootMVCProject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sesac.springBootMVCProject.repository.FreeBoardReplyRepository;
import com.sesac.springBootMVCProject.repository.FreeBoardRepository;
import com.sesac.springBootMVCProject.vo.FreeBoard;
import com.sesac.springBootMVCProject.vo.FreeBoardReply;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@SpringBootTest
public class FreeBoardReplyTest {

	@Autowired
	FreeBoardRepository boardRepo;
	@Autowired
	FreeBoardReplyRepository replyRepo;

	@Test
	public void test9() {
		boardRepo.findAll().forEach(b->{
			System.out.println(b);
		});
	}
	
	
	//@Test
	public void test8() {
		Pageable paging = PageRequest.of(0, 3, Sort.by("bno").descending());
		Page<FreeBoard> result = boardRepo.findByWriter("괴도SUN", paging);
		System.out.println("사이즈 : " + result.getSize());
		System.out.println("TotalElement : "+ result.getTotalElements());
		System.out.println("TotalPage : " + result.getTotalPages());
		System.out.println("next page : " + result.nextPageable());
		
		List<FreeBoard> blist = result.getContent();
		blist.forEach(b->{
			System.out.println(b);
		});
	}
	
	
//	@Test
	public void test7() {
		//Board정보와 댓글의 건수를 가지고 오기
		FreeBoard board = boardRepo.findById(424L).get();
		System.out.println(board);
		System.out.println("댓글의 건수 : " + board.getReplies().size());
		board.getReplies().forEach(reply->{
			System.out.println(reply);
		});
		
	}

	
	
//	@Test
	public void test6() {
		FreeBoard board = boardRepo.findById(424L).get();
		replyRepo.findByBoard(board).forEach(b -> {//foreach는 자바...
			System.out.println("");
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.println(b.getBoard().getWriter() + "보드작성자의 댓글정보------>" + b);
		});
	}

//@Test
	public void test5() {
		// FreeBoardReply에 삽입(insert)
		FreeBoard board = boardRepo.findById(424L).get();
		FreeBoardReply reply = FreeBoardReply.builder().reply("오늘 목요일이라고...? 울고싶다").replyer("울보").board(board) // board
																												// 번호가
																												// 들어가는
																												// 것...
				.build();
		replyRepo.save(reply);
	}

//	@Test
	public void test4() {
		boardRepo.findByTitleContaining("금요일").forEach(b -> {
			System.out.println(b);
			System.out.println("==========================================================");
			System.out.println("");
		});
	}

//	@Test
	public void test3() {
		boardRepo.findAll().forEach(b -> {
			System.out.println(b);
			System.out.println("==========================================================");
		});
	}

//	@Test
	public void test2() {
		// board만 추가
		IntStream.rangeClosed(1, 5).forEach(i -> {

			FreeBoard board = FreeBoard.builder().title("오늘 금요일!" + i).writer("괴도SUN").content("사실 목요일! 풉킥!" + i)
					.build();
			boardRepo.save(board);
		});
	}

//	@Test
	public void test1() {
		System.out.println("---------board추가-------reply도 추가-------------------");

		IntStream.rangeClosed(1, 5).forEach(i -> {

			FreeBoard board = FreeBoard.builder().title("화나게 하지마!!!" + i).writer("SUN").content("장난하니?!" + i).build();

			List<FreeBoardReply> replies = new ArrayList<>();

			for (int j = 1; j <= 3; j++) {
				replies.add(FreeBoardReply.builder().reply("왜 이렇게 화가 잔뜩 났어?").replyer("YOUN" + j).board(board).build());
			}
			board.setReplies(replies);
			boardRepo.save(board);
		});
	}

}
