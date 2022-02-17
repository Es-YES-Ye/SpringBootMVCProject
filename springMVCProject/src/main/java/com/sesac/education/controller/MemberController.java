package com.sesac.education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sesac.education.model.MemberService;

import kr.co.sesac.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	MemberService mService;

	@GetMapping("/member/list")
	public String memberselectAll(Model model) {
		model.addAttribute("memberList", mService.selectAll());
		return "member/list";
	}
	
	  @GetMapping("/member/detail") 
	  public String memberselectById(String mid, Model model) { 
		  model.addAttribute("memberList", mService.selectById(mid)); 
		 return "member/detail"; 
	  }
	 

	@PostMapping("/member/insertMemberPost")
	public String memberInsertPost(MemberVO member, Model model) {
		mService.insertMember(member);
		return "redirect:/member/list";
	}

	@GetMapping("/member/insert")
	public String memberInsert(String mid, Model model) {
		model.addAttribute("member", mService.selectById(mid));
		return "member/addMemberForm";
	}
	
	@PostMapping(value ="/member/update")
	public String memberUpdate(MemberVO member, Model model) {
		mService.insertMember(member);
		return "redirect:/member/list";
	}


	@GetMapping("/member/delete")
	public String memberDelete(String mid, Model model) {
		model.addAttribute("memberList", mService.deleteMember(mid));
		return "member/list";
	}
}