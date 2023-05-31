package kr.co.kmarket2.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket2.dto.ProductDTO;
import kr.co.kmarket2.entity.ProductEntity;
import kr.co.kmarket2.mappers.ProductMapper;
import kr.co.kmarket2.repository.ProductRepo;
import kr.co.kmarket2.service.AdminService;
import lombok.extern.slf4j.Slf4j;

/*
 * 날짜: 2023/02/15
 * 이름: 김지홍
 * 내용: admin/Product controller
 */

@Slf4j
@Controller
public class AdminProductController {
	
	// autowired를 대신하는 생성자

	private final AdminService adminService;
	private final ProductRepo productRepo;
	
	public AdminProductController(AdminService adminService, ProductRepo productRepo) {
		this.adminService = adminService;
		this.productRepo = productRepo;
	}

	// 관리자 상품 목록
	@GetMapping("admin/product/list")
	public String list(Model model,
						@RequestParam(defaultValue = "1") int pageNum
					) {
		
		Pageable pageable = PageRequest.of(pageNum-1, 10, Sort.by("rdate").descending());
		Page<ProductEntity> products = productRepo.findAll(pageable);
		
		int[] pageNumbers = adminService.getPageNumbers(products);

		model.addAttribute("products", products.getContent());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", products.getTotalPages());
		model.addAttribute("startPages", pageNumbers[0]);
		model.addAttribute("endPages", pageNumbers[1]);
		
		return "admin/product/list";
	}
	
	// 관리자 상품등록 페이지
	@GetMapping("admin/product/register")
	public String register() {
		return "admin/product/register";
	}
	
	// 관리자 상품 등록
	@PostMapping("admin/product/register")
	public String insert(ProductDTO dto, HttpServletRequest req) {
		
		// IPv4 입력
		dto.setIp(adminService.getRemoteIP(req));
		
		// 사진 업로드
		List<String> fList= adminService.upload(dto);
		
		//mapstruct 로 dto -> entity 변환
		ProductEntity entity = ProductMapper.INSTANCE.toEntity(dto);
		// 이미지 파일 제목만 entity로 설정
		entity.setThumb1(fList.get(0));
		entity.setThumb2(fList.get(1));
		entity.setThumb3(fList.get(2));
		entity.setDetail(fList.get(3));
		
		adminService.insertProduct(entity);
		
		return "redirect:/admin/product/register";
	}
	
	// 관리자 상품 체크 삭제  02/22
	@PostMapping("admin/product/delete/check")
	@ResponseBody
	public ResponseEntity<Void> deleteCheckQna(@RequestBody int[] checks) {
		log.info("checks :" + checks.toString());
		adminService.deleteAllByProdNoIn(checks);
		return ResponseEntity.ok().build();
	}
	
	// 관리자 상품 목록 개별 삭제  02/22
	@DeleteMapping("admin/product/delete")
	public ResponseEntity<Void> deleteQna(int prodNo) {
		adminService.deleteByProdNo(prodNo);
		return ResponseEntity.ok().build();
	}
}
