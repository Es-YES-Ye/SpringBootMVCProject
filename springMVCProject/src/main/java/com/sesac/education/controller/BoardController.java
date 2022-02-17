package com.sesac.education.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.sesac.education.model.BoardService;

import kr.co.sesac.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	@RequestMapping(value = "/board/list.do", method = RequestMethod.GET)
	public String boardList(Model model,
			HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "contents", required = false) String contents
			) {
		System.out.println(keyword + "--->" + contents);
		
		Map<String,?> map = RequestContextUtils.getInputFlashMap(request);
		if(map != null) {
			model.addAttribute("message", map.get("message"));
		}
		
		model.addAttribute("list", bService.selectAllBoard(keyword, contents));	
		model.addAttribute("boardCnt", bService.boardCnt());	
	
		
		if(keyword==null) return "board/list";
		return "board/list_table";
	}

	@RequestMapping("/board/detail.do")
	//@ResponseBody = 응답 문서의 데이터 보내기
	public String detail(int no, Model model) {
		
		bService.viewCnt(no);
		
		model.addAttribute("fileList", bService.selectFileByNo(no));	
		model.addAttribute("board", bService.selectBoardByNo(no));	
		return "board/detail";
	}

	@RequestMapping("/board/writeForm.do")
	public String formView(Model model) {
		
		model.addAttribute("userVO", model);	
			
		return "board/writeForm";
	}
	
	@RequestMapping(value="/board/write.do", method=RequestMethod.POST)
	public String insert(BoardVO board, RedirectAttributes attr) {
					
		int result = bService.insertBoard(board);
		
		attr.addFlashAttribute("message", result>0?"입력성공!":"입력실패...");
		return "redirect:/board/list.do";
	}

	@RequestMapping(value="/board/update.do")
	public String updateForm(int no, Model model) {
					
		model.addAttribute("board",  bService.selectBoardByNo(no));
		
		return "board/update";
	}

	@RequestMapping(value="/board/update.do", method=RequestMethod.POST)
	public String updateProcess(BoardVO board, RedirectAttributes attr) {
					
		int result = bService.updateBoard(board);
		attr.addFlashAttribute("message", result>0?"수정성공":"수정실패");
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping(value="/board/delete.do")
	public String delete(int no, RedirectAttributes attr) {
					
		int result = bService.deleteBoardByNo(no);		
		attr.addFlashAttribute("message", result>0?"삭제성공!":"삭제실패...");
		return "redirect:/board/list.do";
	}

}
