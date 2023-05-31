package kr.co.kmarket2.controller;

/*
 * 날짜: 2023/02/15
 * 이름:   
 * 내용: admin/cs controller 
 */
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.CsService;
import kr.co.kmarket2.vo.CsNoticeVO;
import kr.co.kmarket2.vo.CsQnaVO;

@Controller
public class CsController {

	@Autowired
	private CsService service;
	
	@GetMapping("cs/index")
	public String index(Model model, String cate1) {
		List<CsNoticeVO> notice = service.selectIndexNotice();
		List<CsQnaVO> qna = service.selectIndexQna(cate1);
		
		model.addAttribute("notice", notice);
		model.addAttribute("qna", qna);
		
		return "cs/index";
	}
	
	
}
