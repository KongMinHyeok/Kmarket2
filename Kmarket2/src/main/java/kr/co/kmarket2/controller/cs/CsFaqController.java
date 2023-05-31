package kr.co.kmarket2.controller.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.CsService;
import kr.co.kmarket2.vo.Cate2VO;
import kr.co.kmarket2.vo.CsFaqVO;

@Controller
public class CsFaqController {

	@Autowired
	private CsService service;
	
	@GetMapping("cs/faq/list")
	public String faqlist(Model model, String cate1) {
		
		List<CsFaqVO> faq = service.selectFaqs(cate1);
		List<Cate2VO> cate2 = service.selectFaqCate(cate1);
		
		model.addAttribute("faq", faq);
		model.addAttribute("cate2", cate2);
		model.addAttribute("cate1", cate1);
		
		return "cs/faq/list";
	}
	
	@GetMapping("cs/faq/view")
	public String faqview(Model model, int no, String cate1) {
		
		CsFaqVO article = service.selectFaq(no);
		
		model.addAttribute("article", article);
		model.addAttribute("cate1", cate1);
		return "cs/faq/view";
	}
}
