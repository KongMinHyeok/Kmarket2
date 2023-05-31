package kr.co.kmarket2.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket2.entity.CsCate2Entity;
import kr.co.kmarket2.entity.CsFaqEntity;
import kr.co.kmarket2.entity.CsNoticeEntity;
import kr.co.kmarket2.entity.CsQnaEntity;
import kr.co.kmarket2.service.AdminService;
import kr.co.kmarket2.utils.PaginationUtils;
import kr.co.kmarket2.vo.CsQnaVO;
import kr.co.kmarket2.vo.PageVO;
import lombok.extern.slf4j.Slf4j;

/*
 * 날짜: 2023/02/15
 * 이름: 김지홍
 * 내용: admin/cs controller 
 */

@Slf4j
@Controller
public class AdminCsController {
	
	private final AdminService adminService;

	public AdminCsController(AdminService adminService) {
		this.adminService = adminService;
	}

	// 관리자 이동 메인
	@GetMapping("admin")
	public String index(Model model) {
		return "admin/index";
	}
	
	// 관리자 공지사항 목록 이동
	@GetMapping("admin/cs/notice/list")
	public String noticeList(
			Model model, 
			@RequestParam(defaultValue = "1", name = "pageNum") int pageNum, 
			@RequestParam(defaultValue = "0", name = "cate1") int cate1) {
		
		Page<CsNoticeEntity> page = adminService.getNoticeList(cate1, pageNum);
		int[] pageNumbers = adminService.getPageNumbers(page);
		
		model.addAttribute("startPages", pageNumbers[0]);
		model.addAttribute("endPages", pageNumbers[1]);
		model.addAttribute("noticeList", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPosts", page.getTotalElements());
		model.addAttribute("cate1", cate1);
		return "admin/cs/notice/list";
		
	}
	// 공지사항
	
	// 공지사항 이동 등록
	@GetMapping("admin/cs/notice/write")
	public String write(Model model, int pageNum, int cate1) {
		model.addAttribute("cate1", cate1);
		model.addAttribute("currentPage", pageNum);
		return "admin/cs/notice/write";
	}
	
	// 공지사항 등록 기능
	@PostMapping("admin/cs/notice/write")
	public String writeNotice(CsNoticeEntity csNoticeEntity, int cate1,HttpServletRequest req) {
		adminService.writeNotice(csNoticeEntity, cate1, req);
		return "redirect:/admin/cs/notice/list";
	}
	
	// 공지사항 이동 수정
	@GetMapping("admin/cs/notice/modify")
	public String modifyNotice(Model model, int no, int pageNum, int cate1)	{
		CsNoticeEntity entity= adminService.getNoticeOne(no);
		model.addAttribute("entity", entity);
		model.addAttribute("cate1", cate1);
		model.addAttribute("currentPage", pageNum);

		return "admin/cs/notice/modify";
	}
	
	// 공지사항 수정 기능
	@PostMapping("admin/cs/notice/modify")
	public String modifyNotice(CsNoticeEntity entity, int cate1, HttpServletRequest req) {
		adminService.modifyNotice(entity, cate1, req);
		return "redirect:/admin/cs/notice/list";
	}
	
	// 공지사항 이동 보기
	@GetMapping("admin/cs/notice/view")
	public String view(int no, Model model, int pageNum, int cate1) {
		CsNoticeEntity entity= adminService.getNoticeOne(no);
		model.addAttribute("entity", entity);
		model.addAttribute("cate1", cate1);
		model.addAttribute("currentPage", pageNum);
		return "admin/cs/notice/view";
	}

	// 공지사항 삭제 기능
	@DeleteMapping("admin/cs/notice/delete")
	public ResponseEntity<Void> deleteNotice(int no) {
		adminService.deleteNotice(no);
		return ResponseEntity.ok().build();
	}

	// 자주묻는질문
	
	// 자주묻는질문 이동 목록
	@GetMapping("admin/cs/faq/list")
	public String faqList(
			Model model, 
			@RequestParam(defaultValue = "1", name = "pageNum") int pageNum, 
			@RequestParam(defaultValue = "0", name = "cate1") int cate1,
			@RequestParam(defaultValue = "0", name = "cate2") int cate2) {
		
//		List<CsFaqEntity> list = csFaqRepo.findAll();
		Page<CsFaqEntity> page = adminService.getFaqList(pageNum, cate1, cate2);
		
		int[] pageNumbers = adminService.getPageNumbers(page);
		
		
		model.addAttribute("startPages", pageNumbers[0]);
		model.addAttribute("endPages", pageNumbers[1]);
		model.addAttribute("list", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPosts", page.getTotalElements());
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate2", cate2);
		return "admin/cs/faq/list";
		
	}
	
	// 묻고답하기 이동 목록
	@GetMapping("admin/cs/qna/list")
	public String qnaList(Model model,
			@RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
			@RequestParam(defaultValue = "0", name = "cate1") int cate1,
			@RequestParam(defaultValue = "0", name = "cate2") int cate2) {
		int offset = adminService.getOffset(pageNum);
		int limit = 10;
		
		// 테이블
		List<CsQnaVO> qnaList = adminService.getQnaList(offset, limit, cate1, cate2);
		model.addAttribute("list", qnaList);
		
		
		List<CsCate2Entity> cate2List = adminService.getCate2List(cate1);
		model.addAttribute("cate2List", cate2List);
		
		// 전체 게시글 수
		int totalCount = adminService.countTotalQna(cate1, cate2);
		model.addAttribute("totalCount", totalCount);
		
		// 페이지네이션 정보
		PageVO pageInfo = PaginationUtils.getPage(10, pageNum, totalCount);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate2", cate2);
		return "admin/cs/qna/list";
	}

	// 묻고답하기 이동 보기
	@GetMapping("admin/cs/qna/view")
	public String qnaWrite(int no, Model model,
			@RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
			@RequestParam(defaultValue = "0", name = "cate1") int cate1,
			@RequestParam(defaultValue = "0", name = "cate2") int cate2) {
		model.addAttribute("qna",adminService.selectQnaById(no));
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("cate1",cate1);
		model.addAttribute("cate2",cate2);
		return "admin/cs/qna/view";
	}
	// 묻고답하기 답변등록
	@PostMapping("admin/cs/qna/modify")
	public String qnaWrite(CsQnaVO vo, int pageNum) {
		CsQnaEntity entity= adminService.updateQna(vo.getNo(), vo.getAnswer());
		log.info("enNo : " + entity.getNo());
		
		return "redirect:/admin/cs/qna/view?cate1="+vo.getCate1()+"&cate2="+vo.getCate2()+"&pageNum="+pageNum+"&no="+vo.getNo();
	}
	
	// 묻고 답하기 삭제
	@DeleteMapping("admin/cs/qna/delete")
	public ResponseEntity<Void> deleteQna(int no) {
		adminService.deleteQna(no);
		return ResponseEntity.ok().build();
	}
	
	// 묻고 답하기 체크 삭제
	@PostMapping("admin/cs/qna/delete/check")
	@ResponseBody
	public ResponseEntity<Void> deleteCheckQna(@RequestBody int[] checks) {
		log.info("checks :" + checks);
		adminService.deleteCheck(checks);
		return ResponseEntity.ok().build();
	}
}
