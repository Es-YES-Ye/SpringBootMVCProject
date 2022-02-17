package com.sesac.education.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sesac.vo.LoginVO;

@Service
public class LoginService {
	@Autowired
	LoginDAO loginDao;
	
	public LoginVO login(LoginVO loginVO) {
		
		return loginDao.login(loginVO);
	}
}
