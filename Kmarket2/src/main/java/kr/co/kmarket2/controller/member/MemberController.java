package kr.co.kmarket2.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket2.service.MemberService;
import kr.co.kmarket2.vo.MemberTermsVO;
import kr.co.kmarket2.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	
	@GetMapping("member/login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("member/join")
	public String join() {
		return "member/join";
	}
	
	@GetMapping("member/register")
	public String register() {
		return "member/register";
	}
	
	@GetMapping("member/registerSeller")
	public String registerSeller() {
		return "member/registerSeller";
	}
	
	@GetMapping("member/signup")
	public String terms(Model model, String type) {
		MemberTermsVO vo = service.selectTerms();
		
		model.addAttribute("type", type);
		model.addAttribute("vo", vo);
		return "member/signup";
	}
	
	// 가입하기
	@PostMapping("member/register")
	public String registerMember(MemberVO vo, HttpServletRequest req) {
		vo.setRegip(req.getRemoteAddr());
		service.insertMember(vo);
		
		return "redirect:/member/login?success=201";
	}
	
	// uid 중복 확인하기
	@ResponseBody
	@PostMapping("member/checkUid")
	public boolean checkUid(String uid) {
		boolean result = service.checkUid(uid);
		return result;
	}
	// uid 중복 확인하기
	@ResponseBody
	@PostMapping("member/checkEmail")
	public boolean checkEmail(String email) {
		boolean result = service.checkEmail(email);
		return result;
	}
	// uid 중복 확인하기
	@ResponseBody
	@PostMapping("member/checkHp")
	public boolean checkHp(String hp) {
		boolean result = service.checkHp(hp);
		return result;
	}
}
