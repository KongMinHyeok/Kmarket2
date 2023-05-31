package kr.co.kmarket2.controller.product;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket2.service.ProductService;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.ProductCartVO;
import kr.co.kmarket2.vo.ProductCate1VO;
import kr.co.kmarket2.vo.ProductCate2VO;
import kr.co.kmarket2.vo.ProductReviewVO;
import kr.co.kmarket2.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;

	@GetMapping("product/list")
	public String list(Model model, String prodCate1, String prodCate2, String pg, String sort) {
		// 카테 출력
		List<ProductCate1VO> cate1 = service.selectCate1();
		List<ProductCate2VO> cate2 = service.selectCate2();

		// 페이징 처리
		int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        int total = 0;
        total = service.selectCountTotal(prodCate1,prodCate2);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getpageStartNum(total, start);
        int[] groups = service.getPageGroup(currentPage, lastPageNum);
//        log.info("1 : " + groups[0]);
        List<ProductVO> products = service.selectProducts(prodCate1, prodCate2, sort);
		
        
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("prodcate1", prodCate1);
        model.addAttribute("prodcate2", prodCate2);
        model.addAttribute("products", products);
		model.addAttribute("sort", sort);
		model.addAttribute("pg", pg);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("groups", groups);
		
		return "product/list";
	}

	@GetMapping("product/view")
	public String view(Model model, String prodCate1, String prodCate2, String prodNo, String pg) {
		// 카테 출력
		List<ProductCate1VO> cate1 = service.selectCate1();
		List<ProductCate2VO> cate2 = service.selectCate2();
		model.addAttribute("cate1",cate1);
		model.addAttribute("cate2",cate2);
		
		//상품출력
		ProductVO product = service.selectProduct(prodNo);
		model.addAttribute("product", product);
		model.addAttribute("prodCate1", prodCate1);
		model.addAttribute("prodCate2", prodCate2);
		
		// review
		int currentPage = service.getCurrentPage2(pg);
        int start = service.getLimitStart2(currentPage);

        int total = service.selectCountTotalReview(prodNo);
        int lastPageNum = service.getLastPageNum2(total);
        int pageStartNum = service.getpageStartNum2(total, start);
        int[] groups = service.getPageGroup2(currentPage, lastPageNum);
		
        List<ProductReviewVO> reviews = service.selectReview(prodNo, start);
        model.addAttribute("reviews", reviews);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("prodNo", prodNo);
		
		
		return "product/view";
	}
	
	@ResponseBody
	@PostMapping("product/cartInert")
	public Map<String, Integer> cartInert(@RequestBody ProductCartVO vo){
		Map<String, Integer> map = new HashMap<>();
		int	result = service.insertCart(vo);
		System.out.println("result" + result);
		map.put("result", result);
		
		return map;
	}
	
	@GetMapping("product/cart")
	public String cart(Model model,Principal principal) {
		// 카테 출력
		List<ProductCate1VO> cate1 = service.selectCate1();
		List<ProductCate2VO> cate2 = service.selectCate2();
		model.addAttribute("cate1",cate1);
		model.addAttribute("cate2",cate2);

		String uid = "admin";
		
		List<ProductCartVO> carts = service.selectCartProduct(uid);
		
		model.addAttribute("carts", carts);
		
		
		
		return "product/cart";
	}
	
	@ResponseBody
	@PostMapping("product/cart")
	public Map<String, Integer> cart(@RequestParam("chks") List<String> chks){
		Map<String, Integer> map = new HashMap<>();
		
		int result = service.deleteCart(chks);
		map.put("result", result);
		
		return map;
	}
	
	@GetMapping("product/order")
	public String order(Model model, @AuthenticationPrincipal MemberVO mem, @RequestParam(value="cartNo", required=false) List<String> cartNo, 
			@RequestParam(value="prodNo", required=false)  String prodNo, @RequestParam(value="count", required=false) String count) {
		
		// 카테 출력
		List<ProductCate1VO> cate1 = service.selectCate1();
		List<ProductCate2VO> cate2 = service.selectCate2();
		model.addAttribute("cate1",cate1);
		model.addAttribute("cate2",cate2);
		
		List<ProductCartVO> cart= null;
		ProductVO product = null;
		
		if(prodNo == null) {
			cart = service.selectCartByCartNo(cartNo);
		}else {
			product = service.selectProduct(prodNo);
		}
		
		model.addAttribute("cart", cart);
		model.addAttribute("count", count);
		model.addAttribute("product", product);
		model.addAttribute("mem", mem);
		
		return "product/order";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
