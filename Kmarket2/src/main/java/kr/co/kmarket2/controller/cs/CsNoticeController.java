package kr.co.kmarket2.controller.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.CsService;
import kr.co.kmarket2.vo.CsNoticeVO;

@Controller
public class CsNoticeController {
	
	@Autowired
	private CsService service;
	
	@GetMapping("cs/notice/list")
	public String noticelist(Model model, String cate1, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		int total = 0;
				
		if(cate1 == null) {
			total = service.selectCountNotices();
		}else {
			total = service.selectCountNotice(cate1);
		}
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		List<CsNoticeVO> notices = service.selectNotices(start);
		List<CsNoticeVO> notice = service.selectNoticesCate(start, cate1);
		model.addAttribute("notices", notices);
		model.addAttribute("notice", notice);
		model.addAttribute("cate1", cate1);
		model.addAttribute("pg", pg);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		return "cs/notice/list";
	}
	
	@GetMapping("cs/notice/view")
	public String noticeview(Model model, int no, String cate1, String pg) {
		
		CsNoticeVO article = service.selectNotice(no);
		
		model.addAttribute("article", article);
		model.addAttribute("cate1", cate1);
		model.addAttribute("pg", pg);
		return "cs/notice/view";
	}
	

	
}
