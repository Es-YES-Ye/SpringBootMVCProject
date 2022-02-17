package kr.co.sesac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SesacController {

	
	@RequestMapping("/hello2.do")
	@ResponseBody//return 옆에 있는 문자열 자체를 출력
	//forward가 아니다.
	public String hello2() {
		return "hello2";
	}
}
