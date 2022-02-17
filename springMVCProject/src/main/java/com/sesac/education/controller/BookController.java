package com.sesac.education.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.co.sesac.vo.BookVO;

@Controller
@RequestMapping("/book")
public class BookController {
	
	List<BookVO> blist = new ArrayList<BookVO>();
	
	public BookController() {

		blist.add(new BookVO(1, "java", "남궁길동", "출판사", "2022-02-08"));
		blist.add(new BookVO(2, "java", "독고길동", "출판사", "2022-02-08"));
		blist.add(new BookVO(3, "java", "선우길동", "출판사", "2022-02-08"));
		blist.add(new BookVO(4, "java", "제갈길동", "출판사", "2022-02-08"));
		blist.add(new BookVO(5, "java", "길동", "출판사", "2022-02-08"));

	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String bookInsert(BookVO book) {
		System.out.println("get : " + book);
		return "book/bookInsertForm";
	}

	/*@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String bookInsertPost2(BookVO book) {
		System.out.println("post : " + book);

		return "book/bookResult";
	}*/

	// @RequestParam : int bookNo = Integer.parseInt(request.getParameter("bno"))
	// @RequestParam 은 변수 이름과 파라미터 이름이 같다면 생략한다.
	//@ModelAttribute("book") : 파라미터로 받은 값을 View 값을 전달하기
	//==model.addAttribute("book2", book3);
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String bookInsertPost(
			@RequestParam("bno") int bookNo, 
			@RequestParam String title, 
			String author, 
			String pub,
			String pubDate, 
			int status, 
			@ModelAttribute("book") BookVO book,
			BookVO book3, 
			Model model, RedirectAttributes redirectAttr) {
	

		model.addAttribute("myname", "SUN");
	    model.addAttribute("book2", book3);
	    blist.add(book3);
	    redirectAttr.addFlashAttribute("message", " 입 력 성 공 ! ");
	    
		return "redirect:/book/list";
	}

//하나의 controller에서의 Exception 처리
//	@ExceptionHandler(Exception.class)
//	public String processException(Exception ex) {
//		ex.printStackTrace();
//		System.out.println("오류: " + ex.getMessage());
//		return "error/errorPage500";
//	}
//	
	
	@RequestMapping("/list")
	public String booklist(Model model, HttpServletRequest request) {
	
		
		//수정에서 list로 redirect인지 새책등록에서 list로 redirect인지 지정한다
		//수정이랑 새책등록에서 set했던 attribute를 request로 얻는다.
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {
			String msg = (String)flashMap.get("message");
			model.addAttribute("message", msg);
		}		
		model.addAttribute("booklist", blist);
		return "book/booklist";
	}
	


	@RequestMapping("/detail")
	public String bookDetail(int bno, Model model) {
		//List에서 bno 찾는다.
		BookVO book = null;

		for(BookVO b : blist) {
			if(b.getBno() == bno) {
				book = b;
				break;
			}
		}
		//정보를 model에 저장한다.
		model.addAttribute("book", book);
		
		//detail페이지에 forward한다	
		return "book/detail"; //forward 위임하다
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BookVO book, Model model, RedirectAttributes redirectAttr) {
		System.out.println("수정된 Book" + book);
		for(BookVO b : blist) {
			if(b.getBno() == book.getBno()) {
				b.setAuthor(book.getAuthor());
				b.setPub(book.getPub());
				b.setPubDate(book.getPubDate());
				b.setStatus(book.getStatus());
				b.setTitle(book.getTitle());
				break;
			}
		}
//		model.addAttribute("message", "수정 성공");
		//redirect에서는 model.addAttribute 사용 불가
		redirectAttr.addFlashAttribute("message", "수 정 성 공 !!");
		return "redirect:/book/list"; //새로운 요청
	}
	
	@RequestMapping(value = "/delete")
	public String delete(int bno, RedirectAttributes redirectAttr) {

		for(BookVO b : blist) {
			if(b.getBno() == bno) {
				blist.remove(b);
					break;
			}
		}

		redirectAttr.addFlashAttribute("message", "삭 제 성 공 !!");
		return "redirect:/book/list"; //새로운 요청
	}
}
