package com.sesac.education.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
//1.설정파일을 이용해서 500error를 전역처리(servlet-context)

//404에러 존재하지 않는 페이지 요청할 때
//전역에서 나는 모든 오류를 이곳에서처리
//2.ControllerAdvice를 이용해서 전역 Exception 처리

@ControllerAdvice //전역Exception
public class ExceptionController {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handler404(HttpServletRequest request, Model model) {
		model.addAttribute("message", "존재하지 않는 페이지입니다.");
		model.addAttribute("url", request.getRequestURL());
		
		return "error/errorPage404";
	}
}
