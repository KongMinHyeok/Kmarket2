package kr.co.kmarket2.controller.cs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.kmarket2.service.CsService;
import kr.co.kmarket2.vo.Cate1VO;
import kr.co.kmarket2.vo.Cate2VO;
import kr.co.kmarket2.vo.CsQnaVO;

@Controller
public class CsQnaController {

	@Autowired
	private CsService service;
	
	@GetMapping("cs/qna/list")
	public String qnalist(Model model, String cate1, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		int total = 0;
		
		total = service.selectCountQnas(cate1);
				
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		List<CsQnaVO> qnas = service.selectQnas(start, cate1);
		
		model.addAttribute("qnas", qnas);
		model.addAttribute("cate1", cate1);
		model.addAttribute("pg", pg);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		return "cs/qna/list";
	}
	
	@GetMapping("cs/qna/view")
	public String qnaview(Model model, int no, String cate1, String pg) {
		
		CsQnaVO article = service.selectQna(no);
		
		model.addAttribute("article", article);
		model.addAttribute("cate1", cate1);
		model.addAttribute("pg", pg);
		return "cs/qna/view";
	}
	
	@GetMapping("cs/qna/write")
	public String qnawrite(Model model, String cate1) {
		
		List<Cate1VO> c1Names = service.selectCate1();
		List<Cate2VO> c2Names = service.selectCate2(cate1);
		
		model.addAttribute("c1Names", c1Names);
		model.addAttribute("c2Names", c2Names);
		model.addAttribute("cate1", cate1);
		
		return "cs/qna/write";
	}
	
	@PostMapping("cs/qna/write")
	public String qnawrite(Model model, CsQnaVO vo, HttpServletRequest req, String cate1) {
		vo.setRegip(req.getRemoteAddr());
		service.insertQna(vo);
		model.addAttribute("cate1", cate1);
		return "redirect:/cs/qna/list?group=qna&cate1=" + cate1;
	}

}
