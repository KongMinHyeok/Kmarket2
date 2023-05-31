
package kr.co.kmarket2.controller.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.kmarket2.entity.CsCate2Entity;
import kr.co.kmarket2.entity.ProductCate2Entity;
import kr.co.kmarket2.entity.ProductEntity;
import kr.co.kmarket2.service.AdminService;

/*
 * 날짜: 2023/02/15
 * 이름: 김지홍
 * 내용: adminRegisterCate2 : cate1의 선택에 따라 cate2를 json 형태로 반환 
 */

@RestController
public class AdminRestController {
	
	private final AdminService adminService;
	
	public AdminRestController(AdminService adminService) {
		this.adminService = adminService;
	}

	// 관리자 상품 카테고리 1차 유형에 따라 2차 반환 select
	@GetMapping("/cate1/{cate1}")
	public List<ProductCate2Entity> adminRegisterCate2(@PathVariable("cate1")int cate1) {
		return adminService.selectAdminCate2(cate1);
	}
	
	// 관리자 상품 리스트 검색
	@GetMapping("/admin/search")
	public Page<ProductEntity> adminList(@RequestParam(defaultValue = "1") int pageNum,
										@RequestParam(required = false) String prodName,
										@RequestParam(required = false) String prodNo,
										@RequestParam(required = false) String company,
										@RequestParam(required = false) String seller){
		Page<ProductEntity> pages = adminService.getProducts(pageNum, prodName, prodNo, company, seller);
		return pages;
	}
	
	// 관리자 고객센터 카테고리1차 에 따라 2차 반환 select
	@GetMapping("/admin/cs/cate2/{cate1}")
	public List<CsCate2Entity> getCate2ByCate1(@PathVariable int cate1){
		return adminService.getCate2List(cate1);
	}
	
}
