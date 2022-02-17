package com.sesac.education.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.sesac.vo.BookVO;

@Controller
@RequestMapping("/test") // type level에 요청주소 작성하기
public class HelloController {

	@RequestMapping("/hello1")//method level에 요청주소 작성하기
	public String hello1(HttpServletRequest request) {
		System.out.println("Hello1요청");
		 return "helloPage1";//페이지로 forward된다	 
		 
		 /*
		  * RequestDispatcher rd = request.getRequestDispatcher
		  * rd.forward(request, response)
		  * 이 방법...
		  * WEB-INF/views/helloPage1.jsp 
		  */
	}
	
	@RequestMapping("/hello2")
	public ModelAndView test2() {
		System.out.println("Hello2요청");
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "SpringFrameWork학습");
		mv.addObject("price", 5000);
		mv.addObject("book", new BookVO(10, "java", "강각감", null, null));
		
		mv.setViewName("helloPage1");
		
		return mv;	 
	}	
	
	@RequestMapping(value = {"/hello3","/hello4.do", "/hello5.test"})
	public String hello3(Model model){
		model.addAttribute("myname", "SUN");
		model.addAttribute("phone", "010-1234-5678");
		
		return "helloPage3";
	}
	
	@RequestMapping(value = {"/hello5"})
	public void hello5(Model model){
		model.addAttribute("myname", "SUN");
		model.addAttribute("phone", "010-1234-5678");
		//hello5.jsp 를 찾는다
		//return이 없을 때 요청의 이름하고 같은 이름.jsp 로 찾는다
	}

	//method = RequestMethod.GET 생략가능 안 쓰면 그냥 get방식이다
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(String userid, String userpass, Model model) {
		
		System.out.println("userid : " + userid);
		System.out.println("userpass : " + userpass);
		if(userid==null) {
			model.addAttribute("message", "로그인 실패....");
		}else {
		model.addAttribute("message", "로그인 성공!");
		}
		return "user/loginResult";
	}
	
	@RequestMapping(value = {"helloParam.do"},
		params = {"userid=sesac", "userpass", "!email"}, method= RequestMethod.GET)
	public String helloParam(Model model, String userid, String userpass) {
		
		System.out.println("userid : " + userid);
		System.out.println("userpass : " + userpass);
		
		model.addAttribute("message", "helloParam.do 로그인 성공!");
		
		return "user/loginResult";
	}
	
}
