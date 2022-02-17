package com.sesac.springBootMVCProject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.reporting.ReportEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sesac.springBootMVCProject.repository.MemberRepository;
import com.sesac.springBootMVCProject.repository.ProfileRepository;
import com.sesac.springBootMVCProject.vo.MemberRole;
import com.sesac.springBootMVCProject.vo.MemberVO;
import com.sesac.springBootMVCProject.vo.ProfileDTO;

import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;

@SpringBootTest
@Log
public class MemberProfileTest {
	
	@Autowired
	MemberRepository mRepo;
	
	@Autowired
	ProfileRepository pRepo;
	
	@Test
	public void test6() {
		List<Object[]> mylist = mRepo.getProfileCountByMember2();
		mylist.forEach(row->{
			//row는 object 배열 
			System.out.println(Arrays.toString(row));
			System.out.println("==================");
		//	System.out.println(row);
		//	System.out.println("==================");
			//for(Object obj:row) {
			//	System.out.println(obj + "-----------------");
			//}
			//System.out.println();
			System.out.println(row[0] + "--->" + row[1]);
		});
		
	}

	
//	@Test
	public void test5() {
		MemberVO member = mRepo.findById("NanGoong1").get();
		List<ProfileDTO> plist = pRepo.findByMember(member);
		plist.forEach(p->{
			//System.out.println(member + "의 프로필 조회");
			//System.out.println(p);
			log.info(p.toString());
			
			
		});
	}
	
//	@Test
	public void test4() {
		//특정 멤버 조회
		System.out.println("======================================특정 멤버 조회================================");
		MemberVO member = mRepo.findById("NanGoong1").get();
		System.out.println(member);
		//특정 프로필 조회->멤버 정보가 온다. 멤버 정보가 들어있다.
		System.out.println("======================================특정 프로필 조회================================");
		ProfileDTO profile = pRepo.findById(113L).get();
		System.out.println(profile);
		System.out.println("======================================프로필의 멤버 상세 정보==============================");
		System.out.println(profile.getMember());
		System.out.println(profile.getMember().getMid());
		System.out.println(profile.getMember().getMname());
		System.out.println(profile.getMember().getMpassword());
		System.out.println("========================================================================================");
	}
	
	
//	@Test
	public void test3() {
//		MemberVO member = mRepo.findById("NanGoong3").orElseThrow();//있다고 하면 멤버가 들어가고 없다고 하면 에러를 일으키는 것
		MemberVO member = mRepo.findById("NanGoong1").get();
		IntStream.rangeClosed(1, 4).forEach(i->{
			ProfileDTO profile = ProfileDTO.builder()
					.fname("나의 이야기" + i)
					.currentYn(i==4?true:false)
					.member(member) //		MemberVO member = mRepo.findById("NanGoong3").get(); 위에 쓴 게 들어간다.
					.build();
			System.out.println(profile);
			pRepo.save(profile);
		});
	}
	
	
	
//	@Test
	public void test2() {
		
		IntStream.rangeClosed(1, 5).forEach(i->{
			MemberVO member = MemberVO.builder()
					.mid("NanGoong" + i)
					.mname("남궁길동" + i)
					.mpassword("1234")
					.mrole(i%2==0?MemberRole.Manager:MemberRole.User)
					.build();
			mRepo.save(member);
		});
		
	}
	
	

//	@Test
	public void test1() {
		
		MemberVO m1 = MemberVO.builder().mid("SUN").mname("썬").build();
		MemberVO m2 = MemberVO.builder().mid("SUN").mname("썬").build();
		
		System.out.println(m1==m2); //주소비교
		System.out.println(m1.equals(m2)); //내용을 비교 mid가 같으면 같은 걸로 친다
		//왜냐하면 MemberVO 에  @EqualsAndHashCode(of = {"mid"}) 이거 걸어놔서
		//만약에 of={} 이거 안 걸어놨으면 mid도 같고 mname 도 같아야 한다.
		
	}
	
}
