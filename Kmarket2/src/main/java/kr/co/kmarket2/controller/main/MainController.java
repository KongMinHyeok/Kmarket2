package kr.co.kmarket2.controller.main;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.ProductService;
import kr.co.kmarket2.vo.ProductCate1VO;
import kr.co.kmarket2.vo.ProductCate2VO;
import kr.co.kmarket2.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Controller
public class MainController {

	@Autowired
	private ProductService service;
	
	@GetMapping(value={"/", "index"})
	public String main(Principal principal ,Model model) {
		// 카테 출력
		List<ProductCate1VO> cate1 = service.selectCate1();
		List<ProductCate2VO> cate2 = service.selectCate2();
		model.addAttribute("cate1",cate1);
		model.addAttribute("cate2",cate2);
		
		List<ProductVO> productsbest = service.selectProductsBest();
		List<ProductVO> productshit = service.selectProductsHit();
		List<ProductVO> productsscore = service.selectProductsScore();
		List<ProductVO> productsnew = service.selectProductsNew();
		List<ProductVO> productsdiscount = service.selectProductsDiscount();
		
		model.addAttribute("best", productsbest);
		model.addAttribute("hit", productshit);
		model.addAttribute("score", productsscore);
		model.addAttribute("latest", productsnew);
		model.addAttribute("discount", productsdiscount);
		model.addAttribute("imagesEndpoint", "images/");
		model.addAttribute("filename", "cat-g893543a6c_640.jpg");

		return "index";
	}
}
