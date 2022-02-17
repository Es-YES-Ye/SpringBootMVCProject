package com.sesac.springBootMVCProject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.sesac.springBootMVCProject.repository.PDSBoardRepository;
import com.sesac.springBootMVCProject.repository.PDSFileRepository;
import com.sesac.springBootMVCProject.vo.PDSBoard;
import com.sesac.springBootMVCProject.vo.PDSFile;

import lombok.extern.java.Log;

@Commit
@SpringBootTest
@Log
public class PDSBoardFileTest {

	@Autowired
	PDSBoardRepository boardRepo;
	@Autowired
	PDSFileRepository fileRepo;
	
	
	
//	@Test
	public void test6() {
		log.info("-----------------OneToMany연습:One은 수정하고 Many는 추가하고자 한다.---------------");
		boardRepo.findById(323L).ifPresentOrElse(b->{
			System.out.println(b);
			System.out.println(b.getFiles2().size() + "건의 FILE");
			System.out.println("===================================수정==============================");
			b.setPname("남궁선우");
			b.setPwriter("독고제갈... 화나게 하지마....");
			b.getFiles2().add(PDSFile.builder()
					.pdsfilename("(｡•̀ᴗ-)✧.jyp")
					.build());
			boardRepo.save(b);
			System.out.println("===========================수정완료==========================");
			System.out.println(b.getPname());
			System.out.println(b.getPwriter());
			System.out.println(b.getFiles2().size());
			System.out.println(b.getFiles2());
		}, ()->{
			System.out.println("없다!");
		});
		
	}
	
	@Transactional
//	@Test
	public void test5() {
		boardRepo.updatePDSFile(285L, "따뜻한 콜드브루.jyp");		
		boardRepo.updatePDSFile(293L, "차가운 군고구마.jyp");		
		boardRepo.updatePDSFile(299L, "식어버린 핫초코.jyp");		
		boardRepo.updatePDSFile(307L, "느긋한 KTX.jyp");		
		boardRepo.updatePDSFile(318L, "초고속 달팽이.jyp");		
		boardRepo.updatePDSFile(345L, "열린 교회 닫힌 문.jyp");		
	}
	
	
	

//	@Test
	public void test4() {
		log.info("-----------------OneToMany연습:삭제---------------");
		fileRepo.deleteById(283L);
		fileRepo.deleteById(284L);
		fileRepo.deleteById(311L);
		fileRepo.deleteById(312L);
		fileRepo.deleteById(313L);
	}

//	@Test
	public void test3() {
//		log.info("------------------OneToMany연습:EAGER--------------");
		log.info("------------------OneToMany연습:LAZY--------------");
		boardRepo.findAll().forEach(b -> {
			System.out.println(b.getPid() + "---->" + b.getFiles2());
		});
	}

//	@Test
	public void test2() {
		log.info("------------------OneToMany연습:EAGER--------------");
		boardRepo.findAll().forEach(b -> {
			System.out.println(b);
		});
	}

//	@Test
	public void test1() {

		log.info("-----------------OneToMany연습------------------------");
		IntStream.rangeClosed(1, 10).forEach(i -> {

			List<PDSFile> filesList = new ArrayList<>();
			IntStream.rangeClosed(100, 105).forEach(j -> {
				filesList.add(PDSFile.builder().pdsfilename(i + "-라는 파일-" + j + ".jyp").build());
			});

			PDSBoard board = PDSBoard.builder().pname("독고제갈" + i).pwriter("열받게하지마!!!" + i % 2).files2(filesList)
					.build();

			boardRepo.save(board);
		});

	}

}
