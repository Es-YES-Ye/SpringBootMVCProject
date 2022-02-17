package com.sesac.education.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.Controller;

import com.sesac.education.model.LoginService;

import kr.co.sesac.vo.LoginVO;

public class LoginProcessController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		LoginVO loginVO = new LoginVO(id, password);
		
		LoginService service = new LoginService();
		LoginVO userVO = service.login(loginVO); 
		
		String url = "";
		if(userVO == null) {
			// 濡쒓렇�씤 �떎�뙣
			url = "/login.do";
		} else {
			// 濡쒓렇�씤 �꽦怨�
			url = "/";
			HttpSession session = request.getSession(); // java�뿉�꽌 session媛앹껜�뒗 �궡�옣媛앹껜媛� �븘�땲湲� �븣臾몄뿉 諛쏆븘���빞�븳�떎.
			session.setAttribute("userVO", userVO);
		}
		
		return "redirect:" + url; 
		// sendRedirect 吏꾪뻾�븷 �븣 sevlet�뿉�꽌 �쓳�떟�쓣 二쇨퀬 �겢�씪�씠�뼵�듃 �슂泥��쓣 �옱諛쒖깮�떆�궓�떎.
		// redirect瑜� �빐�빞�븯�뒗 寃쎌슦�씤吏� �뙋�떒�븯湲� �쐞�빐 �젒�몢�뼱瑜� 遺숈뿬 由ы꽩�븿
	}
	
	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		LoginVO loginVO = new LoginVO(id, password);
		
		LoginService service = new LoginService();
		LoginVO userVO = service.login(loginVO); 
		
		String url = "";
		if(userVO == null) {
			// 濡쒓렇�씤 �떎�뙣
			url = request.getContextPath() + "/login.do";
		} else {
			// 濡쒓렇�씤 �꽦怨�
			url = request.getContextPath();
		}
		
		response.sendRedirect(url);
		
		request.setAttribute("url", url);
		
		return "aaa.jsp";
	}
	*/
}
