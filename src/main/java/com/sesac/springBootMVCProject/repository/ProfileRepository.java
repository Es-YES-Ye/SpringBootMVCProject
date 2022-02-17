package com.sesac.springBootMVCProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sesac.springBootMVCProject.vo.MemberVO;
import com.sesac.springBootMVCProject.vo.ProfileDTO;
//<1,2>
//1. profileDTO를 다루려고
//2. 키는 profileDTO 의 키가 fno고 fno의 타입은 Long
public interface ProfileRepository extends CrudRepository<ProfileDTO, Long> {
	
	//기본 crud 작업: findAll(), findById(), save(), count(), deleteById()
	//규칙에 맞게 메소드를 추가 가능하다
	List<ProfileDTO> findByMember(MemberVO mem);
	

}
